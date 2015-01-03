import cache.RedisManager;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zjh on 14-12-31.
 */
public class CacheTest {
    JedisPool jedisPool = RedisManager.getRedisPoolInstance();

    @Test
    public void getFromRedis(){
        Jedis jedis = null;
        try {
            int vid = 1;
            String key = "video-" + vid;
            jedis = jedisPool.getResource();
            String title = jedis.hget(key, "title");
            String authorname = jedis.hget(key, "authorname");
            String videourl = jedis.hget(key, "videourl");
            String imageurl = jedis.hget(key, "imageurl");

            System.out.println(title + " - " + authorname + " - " + videourl + " - " + imageurl);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisPool.returnResource(jedis);
        }
    }
}
