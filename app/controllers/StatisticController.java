package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import utils.StatisticUtils;

/**
 * Created by zjh on 15-1-8.
 */
public class StatisticController extends Controller {

    public static Result singleVideo(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "video", id);
        return ok();
    }
}

