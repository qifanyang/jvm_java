package vm.runtime;

import vm.parser.*;
import vm.parser.cp.ConstantPoolInfo;
import vm.parser.cp.ConstantUtf8Info;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
public class VirtualMachine {


    public void run(String s) throws Exception {
        //加载对应字节码,寻找main方法
        ClassFile classFile = ClassFileParser.load(s);

        MethodInfo[] methods = classFile.getMethods();
        MethodInfo mainMethodInfo = null;
        for(MethodInfo methodInfo : methods){
            U2 name_index = methodInfo.getName_index();
            ConstantPoolInfo constantPoolInfo = classFile.getConstant_pool_info()[name_index.value];
            IConstantPoolObject constantPoolObject = constantPoolInfo.getConstantPoolObject();
            ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantPoolObject;
            if(utf8Info.string().equals("main")){
                mainMethodInfo = methodInfo;
                break;
            }
        }
        if(null == mainMethodInfo){
            System.out.println("没有主方法");
            return;
        }
        //创建主线程栈
        ThreadStack mainThreadStack = new ThreadStack();


        mainThreadStack.createStackFrame(mainMethodInfo, classFile.getConstant_pool_info());

        mainThreadStack.start();
        //控制权校给主线程栈 ...
    }

    //字节码执行单元

        //方法区实现

        //暂不支持对象,实现方法调用


}
