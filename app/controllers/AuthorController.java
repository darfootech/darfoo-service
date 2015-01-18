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
public class AuthorController extends Controller {
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();
    static String baseUrl = BackendManager.getBackendBaseUrl();

    public static Result singleAuthor(Long id){
        Jedis jedis = null;
        try {
            String key = "author-" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/author/" + id);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/author/" + id);
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

    public static Result indexAuthors(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (!jedis.exists("authorindex")){
                //return redirect(baseUrl + "/cache/author/index");
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/author/index");
                if (statuscode == 200){
                    List<String> keys = jedis.lrange("authorindex", 0L, -1L);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String key : keys){
                        Map<String, String> item = jedis.hgetAll(key);
                        result.add(item);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
            }else{
                List<String> keys = jedis.lrange("authorindex", 0L, -1L);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String key : keys){
                    Map<String, String> item = jedis.hgetAll(key);
                    result.add(item);
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

    public static Result indexAuthorsByPage(Integer page){
        Jedis jedis = null;
        try {
            String rediskey = "authorindexpage" + page;
            jedis = jedisPool.getResource();
            if (!jedis.exists(rediskey)){
                //return redirect(baseUrl + "/cache/author/index");
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/author/index/page/" + page);
                if (statuscode == 200){
                    List<String> keys = jedis.lrange(rediskey, 0L, -1L);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String key : keys){
                        Map<String, String> item = jedis.hgetAll(key);
                        result.add(item);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
            }else{
                List<String> keys = jedis.lrange(rediskey, 0L, -1L);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String key : keys){
                    Map<String, String> item = jedis.hgetAll(key);
                    result.add(item);
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

    public static Result getVideos(Long id){
        Jedis jedis = null;
        try {
            String key = "authorvideos" + id;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/author/videos/" + id);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/author/videos/" + id);
                if (statuscode == 200){
                    Set<String> keys = jedis.smembers(key);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String ikey : keys){
                        Map<String, String> item = jedis.hgetAll(ikey);
                        result.add(item);
                    }
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
            }else{
                Set<String> keys = jedis.smembers(key);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String ikey : keys){
                    Map<String, String> item = jedis.hgetAll(ikey);
                    result.add(item);
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

    public static Result getVideosByPage(Long id, Integer page){
        Jedis jedis = null;

        long start = (page-1) * PageUtils.videopagesize;
        long end = page * PageUtils.videopagesize - 1;

        try {
            String key = "authorvideos" + id + "page";
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/author/videos/" + id);
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/author/videos/" + id + "/page/" + page);
                if (statuscode == 200){
                    List<String> keys = jedis.lrange(key, start, end);
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    for (String ikey : keys){
                        Map<String, String> item = jedis.hgetAll(ikey);
                        result.add(item);
                    }
                    System.out.println("videolist size -> " + result.size());
                    return ok(Json.toJson(result));
                }else{
                    return ok(Json.toJson("error"));
                }
            }else{
                List<String> keys = jedis.lrange(key, start, end);
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String ikey : keys){
                    Map<String, String> item = jedis.hgetAll(ikey);
                    result.add(item);
                }
                System.out.println("videolist size -> " + result.size());
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
            String key = "authorsearch" + content;
            jedis = jedisPool.getResource();
            if (!jedis.exists(key)){
                //return redirect(baseUrl + "/cache/author/search?search=" + URLEncoder.encode(content, "utf-8"));
                int statuscode = new HttpUtils().sendCacheRequest(baseUrl + "/cache/author/search?search=" + URLEncoder.encode(content, "utf-8"));
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
