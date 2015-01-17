package controllers;

import com.avaje.ebean.Ebean;
import models.auth.User;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import persistence.BackendManager;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.CryptUtils;
import utils.HttpUtils;
import utils.QiniuUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjh on 15-1-8.
 */
public class UploadResourceController extends Controller {
    static String baseUrl = BackendManager.getBackendBaseUrl();

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

    /**
     * launcher上传视频之前要先确定上传的video标识也就是名字加后缀是否已经存在了
     * videokey为文件名-mac地址的格式防止冲突.后缀
     * @return
     */
    public static Result prepareUpload(){
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get("username");
        String password = form.get("password");
        String videokey = form.get("videokey");

        boolean flag = User.authenticate(username, password);
        if (flag){
            String requestUrl = baseUrl + "/uploadresource/prepareupload/" + videokey;
            String response = new HttpUtils().getRequest(requestUrl);
            return ok(response);
        }else{
            System.out.println("用户不存在");
            result.put("status", "none");
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

        boolean flag = User.authenticate(username, password);
        if (flag){
            System.out.println("用户已经存在");
            User user = Ebean.find(User.class).where().eq("username", username).findUnique();
            String requestUrl = baseUrl + "/uploadresource/finishcallback";

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("userid", user.getId()+""));
            urlParameters.add(new BasicNameValuePair("videokey", videokey));

            String response = new HttpUtils().postRequest(requestUrl, urlParameters);
            return ok(response);
        }else{
            System.out.println("用户不存在");
            result.put("status", "none");
            return ok(Json.toJson(result));
        }
    }

    /*without auth 第一版自动批量上传暂时不用用户名密码作为上传权限限制，但是需要把mac地址放在文件名之后*/
    public static Result getUploadTokenNoAuth(){
        Map<String, Object> result = new HashMap<String, Object>();
        String token = QiniuUtils.getToken();
        System.out.println("origin token -> " + token);
        String encryptToken = CryptUtils.base64EncodeStr(token);
        result.put("tk", encryptToken);
        return ok(Json.toJson(result));
    }

    /**
     * videokey为文件名-时间戳-mac地址的格式防止冲突.后缀
     * @return
     */
    public static Result uploadFinishCallbackNoAuth(){
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicForm form = Form.form().bindFromRequest();
        String videokey = form.get("videokey");

        System.out.println("videokey -> " + videokey);

        String requestUrl = baseUrl + "/uploadresource/finishcallback";

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("videokey", videokey));

        String response = new HttpUtils().postRequest(requestUrl, urlParameters);
        return ok(response);
    }
}

