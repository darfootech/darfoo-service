package models.statistics;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zjh on 15-1-31.
 */

/*错误日志收集*/
@Entity
public class CrashLog extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String log;

    @Constraints.Required
    public Long timestamp = System.currentTimeMillis() / 1000;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date dueDate = new Date();

    public CrashLog(String log) {
        this.log = log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
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
