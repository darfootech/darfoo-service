package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zjh on 15-1-8.
 */

@Entity
public class ResourceTime extends Model {
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
    public Long timestamp = System.currentTimeMillis() / 1000;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date dueDate = new Date();

    public ResourceTime(String mac, String hostip, String type, Integer resourceid) {
        this.mac = mac;
        this.hostip = hostip;
        this.type = type;
        this.resourceid = resourceid;
    }
}
