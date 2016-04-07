package junit;

import org.junit.Test;
import vm.runtime.VirtualMachine;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class HanZiTest{

    @Test
    public void testHanZi() throws Exception{
        VirtualMachine machine = new VirtualMachine();

        machine.run("source.HanZi");
    }
}
