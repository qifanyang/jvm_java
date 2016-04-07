package test;

import org.junit.Test;

/**
 * @author yangqf
 * @version 1.0 2016/3/29
 */
public class ReaderTest {
    @Test
    public void testReadU4(){

        int i = 128;
        System.out.println(Integer.toBinaryString(i));
        System.out.println((byte)i);
        i = (byte)i;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(i);
        i = 1;
        System.out.println(Integer.toBinaryString(i));
        System.out.println((byte)i);
        //readU4 ���ص���int, �޷���int max = 4294967296, �з���int max = 2147483647
        System.out.println(Integer.MAX_VALUE);
        long x = 4294967296L;
        long z = 0x00_00_00_01_00_00_00_00L;
        System.out.println(Long.toBinaryString(x));
        System.out.println(z);
        int y = (int) x;// 00 00 00 00 ff ff ff ff
        System.out.println(y);

    }
}
