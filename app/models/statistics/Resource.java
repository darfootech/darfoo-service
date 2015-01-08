package models.statistics;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zjh on 15-1-7.
 */

@Entity
public class Resource extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String mac;

    @Constraints.Required
    private String hostip;

    /**
     * video tutorial music author
     */
    @Constraints.Required
    private String type;

    @Constraints.Required
    private Integer resourceid;

    @Constraints.Required
    private Long clickcount;

    public Resource(String mac, String hostip, String type, Integer resourceid, Long clickcount) {
        this.mac = mac;
        this.hostip = hostip;
        this.type = type;
        this.resourceid = resourceid;
        this.clickcount = clickcount;
    }

    public static Finder<Long, Resource> find = new Finder<Long, Resource>(
            Long.class, Resource.class
    );

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getResourceid() {
        return resourceid;
    }

    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
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
