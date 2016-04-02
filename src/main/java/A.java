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
        System.out.println("x + y = "+z);
    }
}
