package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import utils.StatisticUtils;

/**
 * Created by zjh on 15-1-8.
 */
public class StatisticController extends Controller {

    /*for resource statistics*/
    public static Result singleVideo(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "video", id);
        return ok();
    }

    public static Result singleTutorial(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "tutorial", id);
        return ok();
    }

    public static Result singleMusic(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "music", id);
        return ok();
    }

    public static Result singleAuthor(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "author", id);
        return ok();
    }
    /*end of resource statistics*/

    /*for menu statistics*/
    public static Result menu(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateMenu(mac, host, id);
        return ok();
    }
    /*end of menu statistics*/

    /*for tab statistics*/
    /*end of tab statistics*/
}

