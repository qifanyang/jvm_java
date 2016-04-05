package vm.lib;

/**
 * invokevirtual调用方法发现如果是本地方法,则按照类名+Native找到对应的类,使用反射调用对应的方法.
 * 这样做是为了输出内容
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class LogNative{
    public static void println(String s){
        java.lang.System.out.println(s);
    }
    public static void print(String s){
        java.lang.System.out.print(s);
    }

    public static void println(Integer i){
        System.out.println(i);
    }

    public static void print(Integer i){
        System.out.print(i);
    }

    public static void print(Integer i, String s){
        System.out.print(i);
    }
}
