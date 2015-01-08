package models.statistics;

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
public class TabTime extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String mac;

    @Constraints.Required
    public String hostip;

    /**
     * 数字增长从左到右
     */
    @Constraints.Required
    public int tabid;

    @Constraints.Required
    public Long timestamp = System.currentTimeMillis() / 1000;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date dueDate = new Date();

    public TabTime(String mac, String hostip, int tabid) {
        this.mac = mac;
        this.hostip = hostip;
        this.tabid = tabid;
    }
}
