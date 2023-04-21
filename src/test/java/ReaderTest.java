package test;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/29
 */
public class ReaderTest {
    @Test
    public void testReadU4() {
//        -2
//        1000...10(原码) 符号位+值二进制
//        1111...01(反码) 符号位不变+值二进制取反
//        1111...10(补码)	反码+1

        int i = 128;
        System.out.println(Integer.toBinaryString(i));
        System.out.println((byte) i);
        i = (byte) i;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(i);
        i = 1;
        System.out.println(Integer.toBinaryString(i));
        System.out.println((byte) i);
        i = -2;
        System.out.println(Integer.toBinaryString(i));
        System.out.println((byte) i);

        System.out.println(Integer.MAX_VALUE);
        long x = 4294967296L;
        long z = 0x00_00_00_01_00_00_00_00L;
        System.out.println(Long.toBinaryString(x));
        System.out.println(z);
        int y = (int) x;// 00 00 00 00 ff ff ff ff
        System.out.println(y);

        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("D:/xxx"));
            dataOutputStream.writeByte((byte) -2);//负数存储fe, 带符号扩展fffffffe, int 的值依然为-2
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
