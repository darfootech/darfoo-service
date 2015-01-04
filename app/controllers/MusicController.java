package controllers;

import cache.RedisManager;
import persistence.BackendManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zjh on 15-1-3.
 */
public class MusicController extends Controller {
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();
    static String baseUrl = BackendManager.getBackendBaseUrl();

    public static Result singleMusic(Long id){
        Jedis jedis = null;
        try {
            String key = "music-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/resources/music/" + id);
            }else{
                Map<String, String> result = jedis.hgetAll(key);
                return ok(Json.toJson(result));
            }
        }catch (Exception e){
            e.printStackTrace();
            return badRequest("error");
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static Result hottestMusic(){
        Jedis jedis = null;
        try {
            String key = "musichottest";
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/cache/music/hottest");
            }else{
                Set<String> latestVideos = jedis.smembers(key);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String vkey : latestVideos){
                    //System.out.println("vkey -> " + vkey);
                    Map<String, String> video = jedis.hgetAll(vkey);
                    //System.out.println("title -> " + video.get("title"));
                    result.add(video);
                }
                return ok(Json.toJson(result));
            }
        }catch (Exception e){
            e.printStackTrace();
            return badRequest("error");
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static Result category(String category){
        Jedis jedis = null;
        try {
            String key = "musiccategory" + category;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/cache/music/category/" + category);
            }else{
                Set<String> latestVideos = jedis.smembers(key);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String vkey : latestVideos){
                    //System.out.println("vkey -> " + vkey);
                    Map<String, String> video = jedis.hgetAll(vkey);
                    //System.out.println("title -> " + video.get("title"));
                    result.add(video);
                }
                return ok(Json.toJson(result));
            }
        }catch (Exception e){
            e.printStackTrace();
            return badRequest("error");
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static Result search(String content){
        Jedis jedis = null;
        try {
            String key = "musicsearch" + content;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/cache/music/search?search=" + URLEncoder.encode(content, "utf-8"));
            }else{
                Set<String> latestVideos = jedis.smembers(key);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String vkey : latestVideos){
                    Map<String, String> video = jedis.hgetAll(vkey);
                    result.add(video);
                }
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
