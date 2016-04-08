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
        RTClass classRT = loadClass(s);
        ClassFile classFile = classRT.getClassFile();

        MethodInfo[] methods = classFile.getMethods();
        MethodInfo mainMethodInfo = null;
        for(MethodInfo methodInfo : methods){
            U2 name_index = methodInfo.getName_index();
            ConstantPoolInfo constantPoolInfo = classFile.getConstant_pool_info()[name_index.value];
            ConstantPoolObject constantPoolObject = constantPoolInfo.getConstantPoolObject();
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


        mainThreadStack.createStackFrame(mainMethodInfo);

        mainThreadStack.start();
        //控制权校给主线程栈 ...

        mainThreadStack.join();
        //ThreadStack.currentThread().join();//当前线程等待当前线程结束....TODO fuck

    }

    /**
     * 加载指定的类,例如:java.lang.System  java/lang/Sytem
     * @param name
     * @return
     * @throws Exception
     */
    public RTClass loadClass(String name) throws Exception{
        String name1 = name.replace(".", "/");
        ClassFile classFile = ClassFileParser.load(name1);
        RTClass classRT = new RTClass();
        classRT.setClassFile(classFile);
        classRT.init();
        RTMethodArea.register(name1, classRT);
        return classRT;
    }

    //字节码执行单元

        //方法区实现

        //暂不支持对象,实现方法调用


}
