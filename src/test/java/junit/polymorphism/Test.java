package junit.polymorphism;

import vm.runtime.VirtualMachine;

public class Test {

    @org.junit.Test
    public void superMethodCallTest() throws Exception {
        VirtualMachine machine = new VirtualMachine();
        machine.run("source.polymorphism.Son");
    }
}
