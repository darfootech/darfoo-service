import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

/**
 * Created by zjh on 15-1-4.
 */
public class ConfTest {
    @Test
    public void loadRedisConf(){
        Config redisConfig = ConfigFactory.load("redis");
        System.out.println("redis.host -> " + redisConfig.getString("redis.host"));
    }
}
