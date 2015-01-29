import org.junit.Test;
import utils.CryptUtils;

/**
 * Created by zjh on 15-1-10.
 */
public class CryptTest {

    /*分享后的*/
    @Test
    public void cryptresourceid(){
        String id = "3";
        String hash = CryptUtils.base64EncodeStr(id);
        System.out.println("first time hash -> " + hash);
        String rehash = CryptUtils.base64EncodeStr(hash);
        System.out.println("second time hash -> " + rehash);

        String originid = CryptUtils.base64DecodeStr(CryptUtils.base64DecodeStr(rehash));
        System.out.println("origin id -> " + originid);
    }

    @Test
    public void cryptusernamepassword(){
        String username = "username";
        String password = "password";
        System.out.println(CryptUtils.base64EncodeStr(username)); // -> dXNlcm5hbWU=
        System.out.println(CryptUtils.base64EncodeStr(password)); // -> cGFzc3dvcmQ=

        String realusername = "cleantha112233";
        String realpassword = "pppppppp";
        System.out.println(CryptUtils.base64EncodeStr(realusername)); // -> Y2xlYW50aGExMTIyMzM=
        System.out.println(CryptUtils.base64EncodeStr(realpassword)); // -> cHBwcHBwcHA=

        String realusername2 = "ccc333";
        System.out.println(CryptUtils.base64EncodeStr(realusername2)); // -> Y2NjMzMz

    }
}
