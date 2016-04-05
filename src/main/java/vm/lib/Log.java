package vm.lib;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class Log{

    /**
     * 输出字符串
     * @param s
     */
    public  native void println(String s);
    public  native void print(String s);

    public native void println(Integer i);
    public native void print(Integer i);
    public native void print(Integer i, String s);
}
