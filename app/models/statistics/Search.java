package models.statistics;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zjh on 15-1-24.
 */

@Entity
public class Search extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String searchcontent;

    @Constraints.Required
    private String searchtype;

    @Constraints.Required
    public Long timestamp = System.currentTimeMillis() / 1000;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date dueDate = new Date();

    public Search(String searchcontent, String searchtype) {
        this.searchcontent = searchcontent;
        this.searchtype = searchtype;
    }
}
