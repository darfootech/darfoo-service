package cache;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.Play;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zjh on 14-12-31.
 */
public class RedisManager {
    public static JedisPool jedisPool; // 池化管理jedis链接池
    public static Config redisConfig;

    static {
        redisConfig = ConfigFactory.load("redis");
    }

    public static JedisPool getRedisPoolInstance() {
        if (jedisPool == null) {
            /*int maxActive = 600;
            int maxIdle = 300;
            int maxWait = 1000;
            String ip = "localhost";
            int port = 6379;
            String password = "cleantha";
            int timeout = 20000;*/
            int maxActive = redisConfig.getInt("redis.maxActive");
            int maxIdle = redisConfig.getInt("redis.maxIdle");
            int maxWait = redisConfig.getInt("redis.maxWait");
            int timeout = redisConfig.getInt("redis.timeout");
            int port = redisConfig.getInt("redis.port");
            String host = redisConfig.getString("redis.host");
            String password = redisConfig.getString("redis.pass");
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxActive);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            //config.setTestOnBorrow(redisConfig.getBoolean("redis.testOnBorrow"));
            jedisPool = new JedisPool(config, host, port, timeout, password);
        }
        return jedisPool;
    }
}
