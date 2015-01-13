import com.avaje.ebean.Ebean;
import models.statistics.*;
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
                String uuid = "00000000-7174-e6c6-0033-c5870033c587";
                int menuid = 3;
                Menu menu = new Menu(macaddress, hostip, uuid, menuid, 0L);
                menu.save();
            }
        });
    }

    @Test
    public void insertOrUpdateMenu(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                String uuid = "00000000-7174-e6c6-0033-c5870033c587";
                int menuid = 3;
                Menu menu = Ebean.find(Menu.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("uuid", uuid).eq("menuid", menuid).findUnique();

                if (menu != null){
                    menu.updateClickcount();
                }else{
                    Menu newMenu = new Menu(macaddress, hostip, uuid, menuid, 0L);
                    newMenu.save();
                }
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
                String uuid = "00000000-7174-e6c6-0033-c5870033c587";
                int menuid = 3;
                MenuTime menuTime = new MenuTime(macaddress, hostip, uuid, menuid);
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
    public void insertOrUpdateTab(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685-wlan0";
                int tabid = 3;
                Tab tab = Ebean.find(Tab.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("tabid", tabid).findUnique();

                if (tab != null){
                    tab.updateClickcount();
                }else{
                    Tab newTab = new Tab(macaddress, hostip, tabid, 0L);
                    newTab.save();
                }
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
    public void insertOrUpdateResource(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                String resourcetype = "video";
                int resourceid = 3;
                Resource resource = Ebean.find(Resource.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("type", resourcetype).eq("resourceid", resourceid).findUnique();

                if (resource != null){
                    resource.updateClickcount();
                }else{
                    Resource newResource = new Resource(macaddress, hostip, resourcetype, resourceid, 0L);
                    newResource.save();
                }
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
