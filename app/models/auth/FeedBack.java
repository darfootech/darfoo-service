package models.auth;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zjh on 15-1-29.
 */

@Entity
public class FeedBack extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private Long user_id;

    @Constraints.Required
    private String username;

    @Constraints.Required
    private String feedback;

    public FeedBack(Long user_id, String username, String feedback) {
        this.user_id = user_id;
        this.username = username;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
