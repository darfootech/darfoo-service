import com.avaje.ebean.Ebean;
import models.auth.User;
import org.junit.Test;
import utils.CryptUtils;

import javax.persistence.PersistenceException;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by zjh on 15-1-10.
 */
public class UserTest {
    @Test
    public void insertUser(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                try {
                    String username = "cleantha";
                    String password = CryptUtils.base64EncodeStr("pppppppp");
                    User user = new User(username, password);
                    user.save();
                    System.out.println("insert id -> " + user.getId());
                }catch (PersistenceException e){
                    System.out.println("duplicate username insert failed");
                }
            }
        });
    }

    @Test
    public void isExistsUser(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String username = "cleantha333";
                String password = CryptUtils.base64EncodeStr("pppppppp");
                User user = Ebean.find
                (User.class).where().eq("username", username).eq("password", password).findUnique();
                if (user != null){
                    System.out.println("用户已经存在");
                }else{
                    System.out.println("用户不存在");
                }
            }
        });
    }

    @Test
    public void bcryptInsertUser(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                try {
                    String username = "cleantha112233";
                    String password = CryptUtils.base64EncodeStr("pppppppp");

                    boolean flag = User.create(username, password);
                    if (flag){
                        System.out.println("insert successful");
                    }else{
                        System.out.println("insert failed");
                    }
                }catch (PersistenceException e){
                    System.out.println("duplicate username");
                }
            }
        });
    }

    @Test
    public void bcryptUserAuth(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String username = "cleantha333";
                String password = CryptUtils.base64EncodeStr("pppppppp");

                boolean flag = User.authenticate(username, password);
                if (flag){
                    System.out.println("用户已经存在");
                }else{
                    System.out.println("用户不存在");
                }
            }
        });
    }
}
