package utils;

import com.avaje.ebean.Ebean;
import models.statistics.*;

/**
 * Created by zjh on 15-1-8.
 */
public class StatisticUtils {
    public static void insertOrUpdateMenu(String macaddress, String hostip, String uuid, int menuid){
        Menu menu = Ebean.find(Menu.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("uuid", uuid).eq("menuid", menuid).findUnique();

        if (menu != null){
            menu.updateClickcount();
        }else{
            Menu newMenu = new Menu(macaddress, hostip, uuid, menuid, 1L);
            newMenu.save();
        }

        MenuTime menuTime = new MenuTime(macaddress, hostip, uuid, menuid);
        menuTime.save();
    }

    public static void insertOrUpdateTab(String macaddress, String hostip, int tabid){
        Tab tab = Ebean.find(Tab.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("tabid", tabid).findUnique();

        if (tab != null){
            tab.updateClickcount();
        }else{
            Tab newTab = new Tab(macaddress, hostip, tabid, 1L);
            newTab.save();
        }

        TabTime tabTime = new TabTime(macaddress, hostip, tabid);
        tabTime.save();
    }

    public static void insertOrUpdateResource(String macaddress, String hostip, String resourcetype, String uuid, int resourceid){
        Resource resource = Ebean.find(Resource.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("type", resourcetype).eq("uuid", uuid).eq("resourceid", resourceid).findUnique();

        if (resource != null){
            resource.updateClickcount();
        }else{
            Resource newResource = new Resource(macaddress, hostip, resourcetype, uuid, resourceid, 1L);
            newResource.save();
        }

        ResourceTime resourceTime = new ResourceTime(macaddress, hostip, resourcetype, uuid, resourceid);
        resourceTime.save();
    }
}
