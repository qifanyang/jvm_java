import vm.lib.Log;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
public class A{
    private int x;
    private A a = new A();

    public A(){
        int y = 1;
    }

    public A(int x){
        this.x = x;
    }

    public int add(int x, int y){
        return x + y;
    }

    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        A a = new A();
        int z = a.add(x, y);
        Log log = new Log();
        log.print(x);
        log.print(" + ");
        log.print(y);
        log.print(" = ");
        log.println(z);

        log.print(3, "dddddd");

    }
}
