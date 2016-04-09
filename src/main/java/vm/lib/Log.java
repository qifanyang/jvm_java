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
    public native void println(String s);
    public native void print(String s);

    public native void println(int i);
    public native void print(int i);
    public native void print(int i, String s);

    public static native void say(String s);
    public static native void say(int s);

}
