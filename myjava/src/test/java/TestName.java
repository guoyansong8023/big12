import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestName {
    /*
    字符全量输出
     */
    @Test
    public void outAllChars(){
        int colu = 0;
        for (int i = 1; i < 0xffff; i++) {
            System.out.printf("%d : %s\t", i, (char) i);
            colu++;
            if (colu > 10) {
                System.out.println();
                colu=0;
            }
        }
    }


    @Test
    public void testMd5() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] bytes = md5.digest("abc".getBytes());
        StringBuilder sb = new StringBuilder();
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        for (byte b: bytes) {
            sb.append(chars[b>>4 & 0xf]);
            sb.append(chars[b>>0 & 0xf]);
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testRead() throws Exception {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\JavaDemo\\8888.txt"),"unicode");
        char[] buf = new char[1024];
        int len = 0;
        while ((len = isr.read(buf)) != -1) {
            System.out.println(buf);
        }
    }
}
