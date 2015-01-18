package controllers;

import cache.RedisManager;
import persistence.BackendManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.HttpUtils;
import utils.PageUtils;

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
                //return redirect(baseUrl + "/resources/music/" + id);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/resources/music/" + id);
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

    public static Result hottestMusic(){
        Jedis jedis = null;
        try {
            String key = "musichottest";
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/music/hottest");
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/music/hottest");
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
            String key = "musiccategory" + category;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/music/category/" + category);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/music/category/" + category);
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
            String key = "musiccategory" + category + "page" + page;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/music/category/" + category);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/music/category/" + category + "/page/" + page);
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

    public static Result search(String content){
        Jedis jedis = null;
        try {
            String key = "musicsearch" + content;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/music/search?search=" + URLEncoder.encode(content, "utf-8"));
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/music/search?search=" + URLEncoder.encode(content, "utf-8"));
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

    public static Result searchByPage(String content, Integer page){
        Jedis jedis = null;

        long start = (page-1) * PageUtils.musicpagesize;
        long end = page * PageUtils.musicpagesize - 1;

        try {
            String key = "musicsearch" + content + "page";
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/music/search?search=" + URLEncoder.encode(content, "utf-8"));
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/music/search/page/" + page + "?search=" + URLEncoder.encode(content, "utf-8"));
                if (statuscode == 200){
                    List<String> latestVideos = jedis.lrange(key, start, end);
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
                List<String> latestVideos = jedis.lrange(key, start, end);
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
