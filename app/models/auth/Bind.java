package models.auth;

/**
 * Created by zjh on 15-1-10.
 */

import play.data.format.Formats;
import play.db.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * 账户与平板mac地址绑定
 */

@Entity
public class Bind extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private Long user_id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    private String mac;

    @Constraints.Required
    public Long timestamp = System.currentTimeMillis() / 1000;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date dueDate = new Date();

    public Bind(Long user_id, String mac) {
        this.user_id = user_id;
        this.mac = mac;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
