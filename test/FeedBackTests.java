import com.avaje.ebean.Ebean;
import models.auth.FeedBack;
import models.auth.User;
import org.junit.Test;
import utils.CryptUtils;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by zjh on 15-1-29.
 */
public class FeedBackTests {
    @Test
    public void authAndGetFeedBack(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String username = "cleantha112233";
                String password = CryptUtils.base64EncodeStr("pppppppp");
                String feedbackContent = "feedback";

                boolean flag = User.authenticate(username, password);

                if (flag){
                    long userid = Ebean.find(User.class).where().eq("username", username).findUnique().getId();
                    FeedBack feedBack = new FeedBack(userid, username, feedbackContent);
                    feedBack.save();
                    System.out.println("提交反馈成功 -> " + feedBack.getId());
                }else{
                    System.out.println("用户不存在无法提交反馈");
                }
            }
        });
    }
}
