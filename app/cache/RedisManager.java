package cache;

import play.Play;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zjh on 14-12-31.
 */
public class RedisManager {
    public static JedisPool jedisPool; // 池化管理jedis链接池

    /*static {
        Play.application().classloader().getResource("redis.properties");
        int maxActive = Play.application().configuration().getInt("redis.maxActive");
        System.out.println("maxactive -> " + maxActive);
        int maxIdle = Play.application().configuration().getInt("redis.maxIdle");
        int maxWait = Play.application().configuration().getInt("redis.maxWait");
        String ip = Play.application().configuration().getString("redis.host");
        int port = Play.application().configuration().getInt("redis.port");
        String password = Play.application().configuration().getString("redis.pass");
        int timeout = Play.application().configuration().getInt("redis.timeout");
    }*/

    public static JedisPool getRedisPoolInstance() {
        if (jedisPool == null) {
            int maxActive = 600;
            int maxIdle = 300;
            int maxWait = 1000;
            String ip = "localhost";
            int port = 6379;
            String password = "cleantha";
            int timeout = 20000;
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxActive);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            jedisPool = new JedisPool(config, ip, port, timeout, password);
        }
        return jedisPool;
    }
}
