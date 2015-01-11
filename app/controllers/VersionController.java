package controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import persistence.BackendManager;
import play.api.libs.json.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.HttpUtils;

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
        String response = new HttpUtils().getRequest(requestUrl);
        return ok(response);
    }
}
