import org.junit.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Random;

public class HomeWork {
    public byte[] long2Bytes(Long l){
        byte[] b = new byte[8];
        b[0] = (byte)(l>>56);
        b[1] = (byte)(l>>48);
        b[2] = (byte)(l>>40);
        b[3] = (byte)(l>>32);
        b[4] = (byte)(l>>24);
        b[5] = (byte)(l>>16);
        b[6] = (byte)(l>>8);
        b[7] = (byte)(l>>0);
        return b;
    }

    public long bytes2Long(byte[] b){
        long l1 = ((long)b[0] & 0xff) << 56;
        long l2 = ((long)b[1] & 0xff) << 48;
        long l3 = ((long)b[2] & 0xff) << 40;
        long l4 = ((long)b[3] & 0xff) << 32;
        long l5 = ((long)b[4] & 0xff) << 24;
        long l6 = ((long)b[5] & 0xff) << 16;
        long l7 = ((long)b[6] & 0xff) << 8;
        long l8 = ((long)b[7] & 0xff) << 0;
        return l1+l2+l3+l4+l5+l6+l7+l8;
    }

    @Test
    public void toTest(){
        System.out.println(Long.MAX_VALUE);
        System.out.println(bytes2Long(long2Bytes(Long.MAX_VALUE)));
    }

    @Test
    public void abc() throws IOException {
        FileOutputStream fos = new FileOutputStream("D:\\abc.txt");
        fos.write("ab".getBytes("unicode"));
        fos.write("c".getBytes("unicode"));
        fos.close();
    }

    @Test
    public void text(){
        byte b = -1;
        System.out.println(byte2int(b));
    }
    public int byte2int(byte b){
        if (b>=0){
            return b;
        }
        else {
            return b & 0xff;
        }
    }

}
