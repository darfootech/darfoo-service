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
    public Long id;

    @Constraints.Required
    public String mac;

    @Constraints.Required
    public String hostip;

    /**
     * 数字增长从左到有从上到下递增
     */
    @Constraints.Required
    public int menuid;

    @Constraints.Required
    public Long clickcount;

    public Menu(String mac, String hostip, int menuid, Long clickcount) {
        this.mac = mac;
        this.hostip = hostip;
        this.menuid = menuid;
        this.clickcount = clickcount;
    }
}
