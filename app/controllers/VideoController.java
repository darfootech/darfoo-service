package controllers;

import cache.RedisManager;
import persistence.BackendManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.HttpUtils;

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
                //return redirect(baseUrl + "/resources/video/" + id);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/resources/video/" + id);
                if (statuscode == 200){
                    Map<String, String> result = jedis.hgetAll(key);
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
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
                //return redirect(baseUrl + "/cache/video/index");
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/video/index");
                if (statuscode == 200){
                    Set<String> latestVideos = jedis.smembers(key);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String vkey : latestVideos){
                        Map<String, String> video = jedis.hgetAll(vkey);
                        result.add(video);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
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

    public static Result recommendVideos(){
        Jedis jedis = null;
        try {
            String key = "recommend";
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/video/recommend");
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/video/recommend");
                if (statuscode == 200){
                    Set<String> latestVideos = jedis.smembers(key);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String vkey : latestVideos){
                        Map<String, String> video = jedis.hgetAll(vkey);
                        result.add(video);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
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

    public static Result category(String category){
        Jedis jedis = null;
        try {
            String key = "videocategory" + category;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/video/category/" + category);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/video/category/" + category);
                if (statuscode == 200){
                    Set<String> latestVideos = jedis.smembers(key);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String vkey : latestVideos){
                        Map<String, String> video = jedis.hgetAll(vkey);
                        result.add(video);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
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

    public static Result categoryByPage(String category, Integer page){
        Jedis jedis = null;
        try {
            String key = "videocategory" + category + "page" + page;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/video/category/" + category + "/page/" + page);
                if (statuscode == 200){
                    List<String> latestVideos = jedis.lrange(key, 0L, -1L);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String vkey : latestVideos){
                        Map<String, String> video = jedis.hgetAll(vkey);
                        result.add(video);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
            }else{
                List<String> latestVideos = jedis.lrange(key, 0L, -1L);
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

    public static Result getMusic(Long id){
        Jedis jedis = null;
        try {
            String key = "videomusic-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/video/getmusic/" + id);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/video/getmusic/" + id);
                if (statuscode == 200){
                    String mkey = "music-" + jedis.get(key);
                    Map<String, String> result = jedis.hgetAll(mkey);
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
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
                //return redirect(baseUrl + "/cache/video/search?search=" + URLEncoder.encode(content, "utf-8"));
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/video/search?search=" + URLEncoder.encode(content, "utf-8"));
                if (statuscode == 200){
                    Set<String> latestVideos = jedis.smembers(key);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String vkey : latestVideos){
                        Map<String, String> video = jedis.hgetAll(vkey);
                        result.add(video);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
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
