package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by zjh on 15-1-7.
 */
public class HttpUtils {
    private HttpClient httpClient;

    public int sendCacheRequest(String backendUrl){
        if (httpClient == null){
            httpClient = new DefaultHttpClient();
        }

        HttpGet request = new HttpGet(backendUrl);

        try {
            HttpResponse response = httpClient.execute(request);
            int statuscode = response.getStatusLine().getStatusCode();
            return statuscode;
        } catch (IOException e) {
            e.printStackTrace();
            return 500;
        }
    }

}
