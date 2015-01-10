package controllers;

import com.avaje.ebean.Ebean;
import models.auth.User;
import play.data.DynamicForm;
import play.data.Form;
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
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get("username");
        String password = form.get("password");

        //User user = Ebean.find(User.class).where().eq("username", username).eq("password", password).findUnique();
        boolean flag = User.authenticate(username, password);
        if (flag){
            System.out.println("用户已经存在");
            String token = QiniuUtils.getToken();
            System.out.println("origin token -> " + token);
            String encryptToken = CryptUtils.base64EncodeStr(token);
            result.put("tk", encryptToken);
            return ok(Json.toJson(result));
        }else{
            System.out.println("用户不存在");
            result.put("tk", "error");
            return ok(Json.toJson(result));
        }
    }

    public static Result uploadFinishCallback(){
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get("username");
        String password = form.get("password");
        String videokey = form.get("videokey");

        System.out.println("username -> " + username);
        System.out.println("password -> " + password);
        System.out.println("videokey -> " + videokey);

        //User user = Ebean.find(User.class).where().eq("username", username).eq("password", password).findUnique();
        boolean flag = User.authenticate(username, password);
        if (flag){
            System.out.println("用户已经存在");
            result.put("status", "ok");
            return ok(Json.toJson(result));
        }else{
            System.out.println("用户不存在");
            result.put("status", "error");
            return ok(Json.toJson(result));
        }
    }
}
