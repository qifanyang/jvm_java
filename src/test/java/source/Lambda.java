package source;

import java.util.Objects;

/**
 * @author yangqf
 * @version 1.0 2016/10/31
 */
public class Lambda{

    public static void main(String[] args){
        //invokedynamic 返回一个调用点CallSite, name_and_type为 run ()Ljava/lang/runnable
        //所以为函数接口 , lambda表达式创建函数接口对象,该对象实现该接口,然后执行接口调用就执行lambda表达式
        Runnable runnable = () -> System.out.println("i am runnable function interface");
        //使用lambda 编译器会自动创建private static 方法,然后并调用
        //
        runnable.run();

        //集合api,有带有函数接口的参数,当使用lambda时,其实是编译器帮用户做了函数对象的创建
        //集合api中使用lambda缺点有,遍历时无法提前return或break
        //因为遍历的时候执行了方法嵌套调用,foreach中执行accept(就是lambda),return只能终止accept执行
        //使用异常可以跳出for, 但是很丑陋
        //    default void forEach(Consumer<? super T> action) {
        //        Objects.requireNonNull(action);
        //        for (T t : this) {
        //            action.accept(t);
        //        }
        //    }

    }
}
