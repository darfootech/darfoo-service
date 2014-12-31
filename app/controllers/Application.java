package controllers;

import cache.RedisManager;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.*;
import play.libs.Json;
import play.mvc.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import views.html.*;

import java.util.HashMap;
import java.util.Map;

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
}
