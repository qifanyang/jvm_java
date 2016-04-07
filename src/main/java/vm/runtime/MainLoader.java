package vm.runtime;

import org.junit.Test;

/**
 * @author yangqf
 * @version 1.0 2016/4/1
 */
public class MainLoader {

    public static void main(String[] args) throws Exception{
        VirtualMachine machine = new VirtualMachine();

//        machine.run("java.lang.Object");
//        machine.run("test.source.A");
        machine.run("test.source.HanZiTest");

    }



}
