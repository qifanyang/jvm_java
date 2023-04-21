package junit;

import org.junit.Test;
import vm.runtime.VirtualMachine;

public class QuickTest {

    @Test
    public void staticFuncTest() throws Exception {
        VirtualMachine machine = new VirtualMachine();
        //执行类需要包含main方法
        machine.run("source.StaticFunc");
    }
}
