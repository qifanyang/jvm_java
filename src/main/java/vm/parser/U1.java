package vm.parser;

/**
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class U1 {
    public int value;

    public static U1 of(int value) {
        U1 u1 = new U1();
        u1.value = value;
        return u1;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
