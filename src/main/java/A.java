import vm.lib.Log;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
public class A {
    private int x;
    private A a = new A();

    public A(){
        int y = 1;
    }

    public A(int x){
        this.x = x;
    }
    private void test(){
        this.x = 55555555;
        int xx = s(6);
        System.out.println(xx);
    }

    public int s(int z){
    return z;
    }

    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        int z = x + y;
        Log log = new Log();
        log.print(x);
        log.print(" + ");
        log.print(y);
        log.print(" = ");
        log.println(z);

    }
}
