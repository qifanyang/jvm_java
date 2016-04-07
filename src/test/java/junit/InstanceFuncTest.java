package junit;

import org.junit.Test;
import vm.runtime.VirtualMachine;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class InstanceFuncTest{

    @Test
    public void test() throws Exception{
        VirtualMachine machine = new VirtualMachine();

        machine.run("source.InstanceFunc");
    }
}
