package controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import persistence.BackendManager;
import play.api.libs.json.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zjh on 15-1-6.
 */
public class VersionController extends Controller {
    static String baseUrl = BackendManager.getBackendBaseUrl();

    public static Result getLatestVersion(){
        //return redirect(baseUrl + "/resources/version/latest");
        String requestUrl = baseUrl + "/resources/version/latest";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(requestUrl);
        try {
            HttpResponse response = client.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
            System.out.println(result);
            return ok(result.toString());
        } catch (IOException e) {
            //e.printStackTrace();
            return badRequest();
        }
    }
}
