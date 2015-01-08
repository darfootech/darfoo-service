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
    public Long id;

    @Constraints.Required
    public String mac;

    @Constraints.Required
    public String hostip;

    /**
     * video tutorial music author
     */
    @Constraints.Required
    public String type;

    @Constraints.Required
    public Integer resourceid;

    @Constraints.Required
    public Long clickcount;

    public Resource(String mac, String hostip, String type, Integer resourceid, Long clickcount) {
        this.mac = mac;
        this.hostip = hostip;
        this.type = type;
        this.resourceid = resourceid;
        this.clickcount = clickcount;
    }
}
