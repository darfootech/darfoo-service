package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import utils.StatisticUtils;

/**
 * Created by zjh on 15-1-8.
 */
public class StatisticController extends Controller {

    /*for resource statistics*/
    //http://localhost:9000/statistics/video/3/m/00:ad:05:01:a6:85/h/fe80::2ad:5ff:fe01:a685-wlan0
    public static Result singleVideo(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "video", id);
        return ok();
    }

    //http://localhost:9000/statistics/tutorial/3/m/00:ad:05:01:a6:85/h/fe80::2ad:5ff:fe01:a685-wlan0
    public static Result singleTutorial(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "tutorial", id);
        return ok();
    }

    //http://localhost:9000/statistics/music/3/m/00:ad:05:01:a6:85/h/fe80::2ad:5ff:fe01:a685-wlan0
    public static Result singleMusic(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "music", id);
        return ok();
    }

    //http://localhost:9000/statistics/author/3/m/00:ad:05:01:a6:85/h/fe80::2ad:5ff:fe01:a685-wlan0
    public static Result singleAuthor(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateResource(mac, host, "author", id);
        return ok();
    }
    /*end of resource statistics*/

    /*for menu statistics*/
    //http://localhost:9000/statistics/menu/3/m/00:ad:05:01:a6:85/h/fe80::2ad:5ff:fe01:a685-wlan0
    public static Result menu(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateMenu(mac, host, id);
        return ok();
    }
    /*end of menu statistics*/

    /*for tab statistics*/
    //http://localhost:9000/statistics/tab/3/m/00:ad:05:01:a6:85/h/fe80::2ad:5ff:fe01:a685-wlan0
    public static Result tab(Integer id, String mac, String host){
        StatisticUtils.insertOrUpdateTab(mac, host, id);
        return ok();
    }
    /*end of tab statistics*/
}

