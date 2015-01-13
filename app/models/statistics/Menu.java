package models.statistics; /**
 * Created by zjh on 15-1-7.
 */

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用来统计一开始进去的大菜单每一个按钮的使用情况统计总共点击了多少次
 */
@Entity
public class Menu extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String mac;

    @Constraints.Required
    private String hostip;

    @Constraints.Required
    private String uuid;

    /**
     * 数字增长从左到有从上到下递增
     */
    @Constraints.Required
    private int menuid;

    @Constraints.Required
    private Long clickcount;

    public Menu(String mac, String hostip, String uuid, int menuid, Long clickcount) {
        this.mac = mac;
        this.hostip = hostip;
        this.uuid = uuid;
        this.menuid = menuid;
        this.clickcount = clickcount;
    }

    public static Finder<Long, Menu> find = new Finder<Long, Menu>(
            Long.class, Menu.class
    );

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getHostip() {
        return hostip;
    }

    public void setHostip(String hostip) {
        this.hostip = hostip;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public Long getClickcount() {
        return clickcount;
    }

    public void setClickcount(Long clickcount) {
        this.clickcount = clickcount;
    }

    public void updateClickcount(){
        this.clickcount += 1;
        this.update();
    }
}
