package controllers;

import cache.RedisManager;
import models.SingleVideo;
import play.libs.Json;
import play.mvc.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import views.html.*;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Application extends Controller {
    static JedisPool jedisPool = RedisManager.getRedisPoolInstance();

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /*
    Running 10s test @ http://localhost:9000/cache
      4 threads and 10 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
        Latency     0.86ms    2.93ms  49.24ms   98.40%
        Req/Sec     3.44k     0.88k    6.67k    76.26%
      130149 requests in 10.00s, 13.03MB read
    Requests/sec:  13015.26
    Transfer/sec:      1.30MB
    */
    public static Result cache(){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", "cleantha");
        return ok(Json.toJson(result));
    }

    /*
    Running 10s test @ http://localhost:9000/yacache
      4 threads and 10 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
        Latency     1.29ms  459.78us  12.31ms   94.95%
        Req/Sec     1.65k   184.04     2.44k    80.62%
      62162 requests in 9.98s, 10.91MB read
    Requests/sec:   6229.03
    Transfer/sec:      1.09MB
    */
    public static Result yacache(){
        Map<String, Object> result = new HashMap<String, Object>();
        Jedis jedis = null;
        try {
            int vid = 1;
            String key = "video-" + vid;
            jedis = jedisPool.getResource();
            String title = jedis.hget(key, "title");
            String authorname = jedis.hget(key, "authorname");
            String videourl = jedis.hget(key, "videourl");
            String imageurl = jedis.hget(key, "imageurl");

            result.put("id", vid);
            result.put("title", title);
            result.put("authorname", authorname);
            result.put("videourl", videourl);
            result.put("imageurl", imageurl);
            //System.out.println(title + " - " + authorname + " - " + videourl + " - " + imageurl);
            return ok(Json.toJson(result));
        }catch (Exception e){
            e.printStackTrace();
            result.put("status", "error");
            return badRequest(Json.toJson(result));
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    /*
    Running 10s test @ http://localhost:9000/cacheindex/1
      4 threads and 10 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
        Latency     1.25ms  248.90us   6.24ms   88.61%
        Req/Sec     1.65k   156.76     2.00k    81.64%
      62340 requests in 10.00s, 10.94MB read
    Requests/sec:   6234.13
    Transfer/sec:      1.09MB
    */
    public static Result cacheindex(Long id){
        Map<String, Object> result = new HashMap<String, Object>();
        Jedis jedis = null;
        try {
            //int vid = 1;
            String key = "video-" + id;
            jedis = jedisPool.getResource();
            String title = jedis.hget(key, "title");
            String authorname = jedis.hget(key, "authorname");
            String videourl = jedis.hget(key, "videourl");
            String imageurl = jedis.hget(key, "imageurl");

            result.put("id", id);
            result.put("title", title);
            result.put("authorname", authorname);
            result.put("videourl", videourl);
            result.put("imageurl", imageurl);
            //System.out.println(title + " - " + authorname + " - " + videourl + " - " + imageurl);
            return ok(Json.toJson(result));
        }catch (Exception e){
            e.printStackTrace();
            result.put("status", "error");
            return badRequest(Json.toJson(result));
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    /*
    Running 10s test @ http://localhost:9000/yacachefast
      4 threads and 10 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
        Latency     1.25ms  241.61us   6.33ms   87.89%
        Req/Sec     1.65k   150.69     2.11k    77.77%
      62366 requests in 10.00s, 10.59MB read
    Requests/sec:   6236.48
    Transfer/sec:      1.06MB
    */
    public static Result yacachefastjson(){
        Map<String, Object> result = new HashMap<String, Object>();
        Jedis jedis = null;
        try {
            int id = 1;
            String key = "video-" + id;
            jedis = jedisPool.getResource();
            String title = jedis.hget(key, "title");
            String authorname = jedis.hget(key, "authorname");
            String videourl = jedis.hget(key, "videourl");
            String imageurl = jedis.hget(key, "imageurl");

            result.put("id", id);
            result.put("title", title);
            result.put("authorname", authorname);
            result.put("videourl", videourl);
            result.put("imageurl", imageurl);
            //System.out.println(title + " - " + authorname + " - " + videourl + " - " + imageurl);
            //return ok(Json.toJson(result));
            return ok(JSON.toJSONString(result));
        }catch (Exception e){
            e.printStackTrace();
            result.put("status", "error");
            return badRequest(Json.toJson(result));
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    /*
    Running 10s test @ http://localhost:9000/cacheindexfast/1
      4 threads and 10 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
        Latency     1.24ms  254.03us   6.03ms   89.62%
        Req/Sec     1.66k   146.84     2.11k    73.63%
      62894 requests in 10.00s, 10.68MB read
    Requests/sec:   6289.61
    Transfer/sec:      1.07MB
    */
    public static Result cacheindexfastjson(Long id){
        Map<String, Object> result = new HashMap<String, Object>();
        Jedis jedis = null;
        try {
            //int vid = 1;
            String key = "video-" + id;
            jedis = jedisPool.getResource();
            String title = jedis.hget(key, "title");
            String authorname = jedis.hget(key, "authorname");
            String videourl = jedis.hget(key, "videourl");
            String imageurl = jedis.hget(key, "imageurl");

            result.put("id", id);
            result.put("title", title);
            result.put("authorname", authorname);
            result.put("videourl", videourl);
            result.put("imageurl", imageurl);
            //System.out.println(title + " - " + authorname + " - " + videourl + " - " + imageurl);
            //return ok(Json.toJson(result));
            return ok(JSON.toJSONString(result));
        }catch (Exception e){
            e.printStackTrace();
            result.put("status", "error");
            return badRequest(Json.toJson(result));
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static Result cachemodel(Integer id){
        Jedis jedis = null;
        try {
            //int vid = 1;
            String key = "video-" + id;
            jedis = jedisPool.getResource();
            String title = jedis.hget(key, "title");
            String authorname = jedis.hget(key, "authorname");
            String videourl = jedis.hget(key, "videourl");
            String imageurl = jedis.hget(key, "imageurl");

            //System.out.println(title + " - " + authorname + " - " + videourl + " - " + imageurl);
            return ok(Json.toJson(new SingleVideo(id, title, authorname, videourl, imageurl)));
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("status", "error");
            return badRequest(Json.toJson(result));
        }finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static Result cachemodelfastjson(Integer id){
        Jedis jedis = null;
        try {
            //int vid = 1;
            String key = "video-" + id;
            jedis = jedisPool.getResource();
            String title = jedis.hget(key, "title");
            String authorname = jedis.hget(key, "authorname");
            String videourl = jedis.hget(key, "videourl");
            String imageurl = jedis.hget(key, "imageurl");

            //System.out.println(title + " - " + authorname + " - " + videourl + " - " + imageurl);
            //return ok(Json.toJson(result));
            return ok(JSON.toJSONString(new SingleVideo(id, title, authorname, videourl, imageurl)));
        }catch (Exception e){
            Map<String, Object> result = new HashMap<String, Object>();
            e.printStackTrace();
            result.put("status", "error");
            return badRequest(Json.toJson(result));
        }finally {
            jedisPool.returnResource(jedis);
        }
    }
}
