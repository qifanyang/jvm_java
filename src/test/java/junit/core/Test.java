package junit.core;

import vm.runtime.VirtualMachine;

public class Test {

    @org.junit.Test
    public void testCreateObject() throws Exception {
        VirtualMachine virtualMachine = new VirtualMachine();
        virtualMachine.run("source.core.Pojo");
    }
}
