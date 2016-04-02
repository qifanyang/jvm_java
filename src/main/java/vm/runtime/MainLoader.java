package vm.runtime;

import org.junit.Test;

/**
 * 完成类似java -jar xxx.jar, 创建一个虚拟机,并加载解析字节码
 * @author yangqf
 * @version 1.0 2016/4/1
 */
public class MainLoader {

    public static void main(String[] args) throws Exception{
        //创建虚拟机
        VirtualMachine machine = new VirtualMachine();

        machine.run("A");

        //加载启动程序
    }



}
