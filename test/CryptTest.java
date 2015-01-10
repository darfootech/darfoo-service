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
}
