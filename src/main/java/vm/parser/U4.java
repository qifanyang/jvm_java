package vm.parser;

/**
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class U4 {
    public long value;

    public static U4 of(long value){
        U4 u4 = new U4();
        u4.value = value;
        return u4;
    }
}
