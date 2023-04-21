package vm.parser;

/**
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class U4 {
    //2^31=2147483648=2G, 单个字节码不可能有这么大,所以使用有符号的int,读取无符号4字节不会发生越界
    public int value;

    public static U4 of(int value) {
        U4 u4 = new U4();
        u4.value = value;
        return u4;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
