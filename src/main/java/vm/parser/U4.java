package vm.parser;

/**
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class U4 {
    public int value;

    public static U4 of(int value){
        U4 u4 = new U4();
        u4.value = value;
        return u4;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
