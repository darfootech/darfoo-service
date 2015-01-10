import com.avaje.ebean.Ebean;
import models.auth.Bind;
import org.junit.Test;

import javax.persistence.PersistenceException;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by zjh on 15-1-10.
 */
public class BindTest {
    @Test
    public void isBindMac(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String mac = "00:ad:05:01:a6:85";
                Bind bind = Ebean.find(Bind.class).where().eq("mac", mac).findUnique();

                if (bind != null){
                    System.out.println("mac地址已经绑定账号了");
                }else{
                    System.out.println("mac地址还没有绑定账号");
                }
            }
        });
    }

    @Test
    public void newBind(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                try {
                    long userid = 3;
                    String mac = "01:ad:05:01:a6:85";
                    Bind bind = new Bind(userid, mac);
                    bind.save();
                    System.out.println("insert id -> " + bind.getId());
                }catch (PersistenceException e){
                    System.out.println("duplicate mac insert failed");
                }
            }
        });
    }
}
