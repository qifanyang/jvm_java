package source;

import vm.lib.Log;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class InstanceFunc{

    public int add(int x, int y){
        return x + y;
    }

    public static void main(String[] args){
        InstanceFunc instanceFuncTest = new InstanceFunc();
        int result = instanceFuncTest.add(1, 2);
        Log log = new Log();
        log.print("1 + 2 =");
        log.print(result);
    }
}
