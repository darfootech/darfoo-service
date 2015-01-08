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
    public Long clickcount;

    public Tab(String mac, String hostip, int tabid, Long clickcount) {
        this.mac = mac;
        this.hostip = hostip;
        this.tabid = tabid;
        this.clickcount = clickcount;
    }
}
