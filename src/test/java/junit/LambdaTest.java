package junit;

import org.junit.Test;
import vm.runtime.VirtualMachine;

/**
 * @author yangqf
 * @version 1.0 2016/10/31
 */
public class LambdaTest {
    @Test
    public void t() throws Exception {
        VirtualMachine machine = new VirtualMachine();
        machine.run("source.lamda.Lambda");
    }
}
