package junit;

import org.junit.Test;
import vm.runtime.VirtualMachine;

/**
 * @author yangqf
 * @version 1.0 2016/4/8
 */
public class CommTest {

    @Test
    public void staticFuncTest() throws Exception {
        VirtualMachine machine = new VirtualMachine();
        machine.run("source.StaticFunc");
    }


    @Test
    public void syncTest() throws Exception {
        VirtualMachine machine = new VirtualMachine();
        machine.run("source.Sync");
    }

    @Test
    public void genericTypeTest() throws Exception {
        VirtualMachine machine = new VirtualMachine();
        machine.run("source.genericity.GenericType");
    }
}
