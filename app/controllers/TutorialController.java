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
public class TutorialController extends Controller {
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();
    static String baseUrl = BackendManager.getBackendBaseUrl();

    public static Result singleTutorial(Long id){
        Jedis jedis = null;
        try {
            String key = "tutorial-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/resources/video/tutorial/" + id);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/resources/video/tutorial/" + id);
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

    public static Result category(String category){
        Jedis jedis = null;
        try {
            String key = "tutorialcategory" + category;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/tutorial/category/" + category);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/tutorial/category/" + category);
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
            String key = "tutorialcategory" + category + "page" + page;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/tutorial/category/" + category);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/tutorial/category/" + category + "/page/" + page);
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
            String key = "tutorialsearch" + content;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/tutorial/search?search=" + URLEncoder.encode(content, "utf-8"));
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/tutorial/search?search=" + URLEncoder.encode(content, "utf-8"));
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

    public static Result getSideBarTutorials(Integer id){
        Jedis jedis = null;
        try {
            String key = "tutorialsidebar" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/tutorial/sidebar/" + id);
                if (statuscode == 200){
                    List<String> sidebarvideos = jedis.lrange(key, 0L, -1L);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String vkey : sidebarvideos){
                        Map<String, String> video = jedis.hgetAll(vkey);
                        result.add(video);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
            }else{
                List<String> sidebarvideos = jedis.lrange(key, 0L, -1L);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String vkey : sidebarvideos){
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
