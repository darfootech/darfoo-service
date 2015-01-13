package models.statistics;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zjh on 15-1-7.
 */

@Entity
public class Tab extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String mac;

    @Constraints.Required
    private String hostip;

    @Constraints.Required
    private String uuid;

    /**
     * 数字增长从左到右
     */
    @Constraints.Required
    private int tabid;

    @Constraints.Required
    private Long clickcount;

    public Tab(String mac, String hostip, String uuid, int tabid, Long clickcount) {
        this.mac = mac;
        this.hostip = hostip;
        this.uuid = uuid;
        this.tabid = tabid;
        this.clickcount = clickcount;
    }

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

    public int getTabid() {
        return tabid;
    }

    public void setTabid(int tabid) {
        this.tabid = tabid;
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
