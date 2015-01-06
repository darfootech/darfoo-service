package controllers;

import persistence.BackendManager;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by zjh on 15-1-6.
 */
public class VersionController extends Controller {
    static String baseUrl = BackendManager.getBackendBaseUrl();

    public static Result getLatestVersion(){
        return redirect(baseUrl + "/resources/version/latest");
    }
}
