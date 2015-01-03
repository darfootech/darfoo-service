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
public class VideoController extends Controller {
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();

    public static Result singleVideo(Long id){
        Jedis jedis = null;
        try {
            String key = "video-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect("http://localhost:8080/darfoobackend/rest/resources/video/" + id);
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

    public static Result indexVideos(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (!jedis.exists("videoindex")){
                return redirect("http://localhost:8080/darfoobackend/rest/cache/video/index");
            }else{
                Set<String> latestVideos = jedis.smembers("videoindex");
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

    public static Result recommendVideos(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (!jedis.exists("videorecommend")){
                return redirect("http://localhost:8080/darfoobackend/rest/cache/video/recommend");
            }else{
                Set<String> latestVideos = jedis.smembers("videorecommend");
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
            String key = "videocategory"+category;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect("http://localhost:8080/darfoobackend/rest/cache/video/category/" + category);
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

    public static Result getMusic(Long id){
        Jedis jedis = null;
        try {
            String key = "videomusic-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect("http://localhost:8080/darfoobackend/rest/cache/video/getmusic/" + id);
            }else{
                String mkey = "music-" + jedis.get(key);
                Map<String, String> result = jedis.hgetAll(mkey);
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
