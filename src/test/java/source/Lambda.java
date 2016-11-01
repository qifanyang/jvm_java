package source;

/**
 * @author yangqf
 * @version 1.0 2016/10/31
 */
public class Lambda{

    public static void main(String[] args){
        Runnable runnable = () -> System.out.println("i am runnable function interface");
        runnable.run();
    }
}
