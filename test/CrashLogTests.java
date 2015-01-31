import com.avaje.ebean.Ebean;
import models.auth.User;
import models.statistics.CrashLog;
import org.junit.Test;
import utils.CryptUtils;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by zjh on 15-1-31.
 */
public class CrashLogTests {
    @Test
    public void newCrashLog() {
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String logcontent = "crash log";
                CrashLog log = new CrashLog(logcontent);
                log.save();
                System.out.println("new crash happend");

            }
        });
    }
}
