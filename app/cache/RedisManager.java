package cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zjh on 14-12-31.
 */
public class RedisManager {
    public static JedisPool jedisPool; // 池化管理jedis链接池

    public static JedisPool getRedisPoolInstance() {
        if (jedisPool == null) {
            int maxActive = 600;
            int maxIdle = 300;
            int maxWait = 1000;
            String ip = "localhost";
            int port = 6379;
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxActive);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            jedisPool = new JedisPool(config, ip, port, 20000, "cleantha");
        }
        return jedisPool;
    }
}
