import com.avaje.ebean.Ebean;
import models.*;
import org.junit.Test;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by zjh on 15-1-7.
 */
public class StatisticTest {
    @Test
    public void insertMenu(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int menuid = 3;
                Menu menu = new Menu(macaddress, hostip, menuid, 0L);
                menu.save();
            }
        });
    }

    @Test
    public void insertMenuTime(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int menuid = 3;
                MenuTime menuTime = new MenuTime(macaddress, hostip, menuid);
                menuTime.save();
            }
        });
    }

    @Test
    public void insertTab(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int tabid = 3;
                Tab tab = new Tab(macaddress, hostip, tabid, 0L);
                tab.save();
            }
        });
    }

    @Test
    public void insertTabTime(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int tabid = 3;
                TabTime tabTime = new TabTime(macaddress, hostip, tabid);
                tabTime.save();
            }
        });
    }

    @Test
    public void insertResource(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                String resourcetype = "video";
                int resourceid = 333;
                Resource resource = new Resource(macaddress, hostip, resourcetype, resourceid, 0L);
                resource.save();
            }
        });
    }

    @Test
    public void insertResourceTime(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                String resourcetype = "video";
                int resourceid = 333;
                ResourceTime resourceTime = new ResourceTime(macaddress, hostip, resourcetype, resourceid);
                resourceTime.save();
            }
        });
    }
}
