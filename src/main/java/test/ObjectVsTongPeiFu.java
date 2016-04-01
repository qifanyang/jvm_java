package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqf
 * @version 1.0 2016/4/1
 */
public class ObjectVsTongPeiFu {
    @Test
    public void t() {
        ArrayList l1 = new ArrayList<>();
//        l1.add(new Object()); 无法通过编译, 通配符不定于Object

    }
}
