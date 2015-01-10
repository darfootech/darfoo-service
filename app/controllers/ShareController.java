package controllers;

import cache.RedisManager;
import persistence.BackendManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.CryptUtils;
import utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjh on 15-1-10.
 */
public class ShareController extends Controller {
    static String baseUrl = BackendManager.getBackendBaseUrl();
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();

    public static Result shareResource(String type, String idhash){
        String originid = CryptUtils.base64DecodeStr(CryptUtils.base64DecodeStr(idhash));
        Map<String, Object> result = new HashMap<String, Object>();

        Jedis jedis = null;
        try {
            String key = type + "-" + originid;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/resources/" + type + "/" + originid);
                if (statuscode == 200){
                    String video_url = jedis.hget(key, "video_url");
                    result.put("play", video_url);
                    return ok(Json.toJson(result));
                }else{
                    result.put("play", "error");
                    return ok(Json.toJson(result));
                }
            }else{
                String video_url = jedis.hget(key, "video_url");
                result.put("play", video_url);
                return ok(Json.toJson(result));
            }
        }catch (Exception e){
            e.printStackTrace();
            return badRequest("error");
        }finally {
            jedisPool.returnResource(jedis);
        }
    }
}
