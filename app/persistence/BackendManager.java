package persistence;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by zjh on 15-1-4.
 */
public class BackendManager {
    public static Config backendConfig;
    public static String baseurl;

    static {
        backendConfig = ConfigFactory.load("backend");
        baseurl = backendConfig.getString("backend.baseurl");
    }

    public static String getBackendBaseUrl(){
        if (baseurl == null) {
            baseurl = backendConfig.getString("backend.baseurl");
        }
        return baseurl;
    }
}
