package controllers;

import cache.RedisManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zjh on 15-1-2.
 */
public class TutorialController extends Controller {
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();

    public static Result singleTutorial(Long id){
        Jedis jedis = null;
        try {
            String key = "tutorial-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect("http://localhost:8080/darfoobackend/rest/cache/video/tutorial/" + id);
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

    public static Result category(String category){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (!jedis.exists("tutorialcategory")){
                return redirect("http://localhost:8080/darfoobackend/rest/cache/tutorial/category/" + category);
            }else{
                Set<String> latestVideos = jedis.smembers("tutorialcategory");
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
}
