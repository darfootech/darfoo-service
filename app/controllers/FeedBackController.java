package controllers;

import com.avaje.ebean.Ebean;
import models.auth.FeedBack;
import models.auth.User;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.CryptUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjh on 15-1-29.
 */
public class FeedBackController extends Controller {

    public static Result getFeedBack(){
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicForm form = Form.form().bindFromRequest();
        String username = CryptUtils.base64DecodeStr(form.get("dXNlcm5hbWU="));
        String password = form.get("cGFzc3dvcmQ=");
        String content = form.get("feedback");

        boolean flag = User.authenticate(username, password);

        if (flag){
            long userid = Ebean.find(User.class).where().eq("username", username).findUnique().getId();
            FeedBack feedBack = new FeedBack(userid, username, content);
            feedBack.save();
            System.out.println("提交反馈成功 -> " + feedBack.getId());
            result.put("status", "ok");
            return ok(Json.toJson(result));
        }else{
            System.out.println("用户不存在无法提交反馈");
            result.put("status", "error");
            return ok(Json.toJson(result));
        }
    }
}
