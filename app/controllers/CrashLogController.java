package controllers;

import models.statistics.CrashLog;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjh on 15-1-31.
 */
public class CrashLogController extends Controller {

    public static Result newCrash() {
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicForm form = Form.form().bindFromRequest();
        String logcontent = form.get("logcontent");

        CrashLog log = new CrashLog(logcontent);
        log.save();
        if (log.getId() > 0) {
            result.put("status", "ok");
        } else {
            result.put("status", "error");
        }

        return ok(Json.toJson(result));
    }
}
