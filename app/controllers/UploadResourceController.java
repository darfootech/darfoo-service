package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.CryptUtils;
import utils.QiniuUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjh on 15-1-8.
 */
public class UploadResourceController extends Controller {

    public static Result getUploadToken(){
        String token = QiniuUtils.getToken();
        System.out.println("origin token -> " + token);
        String encryptToken = CryptUtils.base64EncodeStr(token);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("tk", encryptToken);
        return ok(Json.toJson(result));
    }
}
