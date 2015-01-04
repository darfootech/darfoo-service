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
 * Created by zjh on 15-1-2.
 */
public class VideoController extends Controller {
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();
    static String baseUrl = BackendManager.getBackendBaseUrl();

    public static Result singleVideo(Long id){
        Jedis jedis = null;
        try {
            String key = "video-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/resources/video/" + id);
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
            String key = "videoindex";
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/cache/video/index");
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

    public static Result recommendVideos(){
        Jedis jedis = null;
        try {
            String key = "recommend";
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/cache/video/recommend");
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
            String key = "videocategory" + category;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/cache/video/category/" + category);
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
                return redirect(baseUrl + "/cache/video/getmusic/" + id);
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

    public static Result search(String content){
        Jedis jedis = null;
        try {
            String key = "videosearch" + content;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                return redirect(baseUrl + "/cache/video/search?search=" + URLEncoder.encode(content, "utf-8"));
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
