package vm.parser;

/**
 * 存储无符号short
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class U2 {
    public int value;

    public static U2 of(int value) {
        U2 u2 = new U2();
        u2.value = value;
        return u2;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
