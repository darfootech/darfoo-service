package utils;

import com.avaje.ebean.Ebean;
import models.statistics.Menu;
import models.statistics.Resource;
import models.statistics.Tab;

/**
 * Created by zjh on 15-1-8.
 */
public class StatisticUtils {
    public static void insertOrUpdateMenu(String macaddress, String hostip, int menuid){
        Menu menu = Ebean.find(Menu.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("menuid", menuid).findUnique();

        if (menu != null){
            menu.clickcount += 1;
            menu.update();
        }else{
            Menu newMenu = new Menu(macaddress, hostip, menuid, 0L);
            newMenu.save();
        }
    }

    public static void insertOrUpdateTab(String macaddress, String hostip, int tabid){
        Tab tab = Ebean.find(Tab.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("tabid", tabid).findUnique();

        if (tab != null){
            tab.clickcount += 1;
            tab.update();
        }else{
            Tab newTab = new Tab(macaddress, hostip, tabid, 0L);
            newTab.save();
        }
    }

    public static void insertOrUpdateResource(String macaddress, String hostip, String resourcetype, int resourceid){
        Resource resource = Ebean.find(Resource.class).where().eq("mac", macaddress).eq("hostip", hostip).eq("type", resourcetype).eq("resourceid", resourceid).findUnique();

        if (resource != null){
            resource.clickcount += 1;
            resource.update();
        }else{
            Resource newResource = new Resource(macaddress, hostip, resourcetype, resourceid, 0L);
            newResource.save();
        }
    }
}
