package source.genericity;

/**
 * @author yangqf
 * @version 1.0 2016/4/15
 */
public class GenericType<T, C> {
    T t;//字节码中存储的是Object, 所以可以调用Object的所有方法

    public GenericType() {
        Class<?> aClass = t.getClass();
    }

    public static void main(String[] args) {
        //和不适用泛型字节码一样的,只有在使用的时候字节码会强转,这里这么写应该是用于编译器生成强转字节码
        GenericType<Integer, Integer> tt = new GenericType<>();
        tt.t = 5;
        //这里会有一个 checkcast的强制转换, 在字节码中是存在的, 类似在代码中(Integer)tt.t; ide支持所以不需要明确转换
        //强制转换,是根据objectref做指向的class和指定要被转换成的class比较是否一样来判断是否可以强转
        Integer get = tt.t;
        int i = get + 1;
    }
}
