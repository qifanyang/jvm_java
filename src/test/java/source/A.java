package source;

import vm.lib.Log;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
public class A{
    private int x;

    public int add(int x, int y){
        return x + y;
    }

    public static void main(String[] args) {
        Log log = new Log();
        for(int i = 0; i < 3; i++){
           log.println("fuck fuck");
        }
        int x = 10;
        int y = 20;
        A a = new A();
        y = a.add(x, y);
        if(y == 30){
            y = 1;
            log.println("execute if ...");
        }
        log.print(x);

    }
}
