package models.auth;

import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zjh on 15-1-10.
 */

@Entity
public class User extends Model {
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    private String username;

    @Constraints.Required
    private String password;

    public static Finder<Long, User> find = new Finder(Long.class, User.class);

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Boolean create(String username, String password) {
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()));
        user.save();
        if (user.getId() > 0){
            return true;
        }else{
            return false;
        }
    }

    public static Boolean authenticate(String username, String password) {
        User user = User.find.where().eq("username", username).findUnique();
        try {
            if (user != null && BCrypt.checkpw(password, user.password)) {
                return true;
            } else {
                return false;
            }
        }catch (IllegalArgumentException e){
            return false;
        }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
