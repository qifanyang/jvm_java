package vm.opcode;

import vm.parser.cp.ConstantClassInfo;
import vm.parser.cp.ConstantUtf8Info;
import vm.runtime.*;

/**
 * new没法作为类名,使用newnew代替new<br>
 * 操作数为一个常量池索引,指向的常量池元素为一个class或者interface
 * 然后 Memory for a new instance of that class is allocated from the garbage-collected heap
 * 从带有垃圾回收功能的heap中分配一个实例对象,初始化实例变量为默认值,然后将新建的对象引用压入操作数栈
 *
 * 为实例对象分配内存,需要的空间为,当前类中声明的实例变量,在父类中声明的实例变量并且包含私有的
 *
 * 注意:new指令并没有完全创建一个新的实例,实例初始化方法<init>被调用后,才表示一个对象被创建完成
 *
 * JLS 12.5定义了new方法初始化过程
 * 1.调用构造方法,一直递归调用父类的构造方法,直到调用到Object的构造方法才停止递归
 *
 * 2.递归返回,从上至下,开始执行实例变量声明时的初始化表达式(虚拟机层面有默认值初始化jvm2.3),然后再执行对应构造方法体, 从Object开始...
 *
 * java语言的构造器方法在jvm层面对应<init>, 一个构造器对应一个<init>方法
 *
 * new Xxx();
 * 执行步骤1,调用父类构造方法
 * 执行步骤2,初始化实体变量值(分为虚拟机默认值)
 * 执行构造方法体,对应<init>, 在声明时设置的时也在<init>方法中执行 (同理<clinit>执行static字段赋值)
 *
 * new Integer(1)
 * 1.new Integer 分配空间,并使用虚拟机默认值初始化实例变量值
 * 2.dup
 * 3.iconst_1
 * 4.invokespecial  #6                  // Method "<init>":(I)V   //执行构造方法,(包含在声明时设置的时)
 *
 * @author yangqf
 * @version 1.0 2016/4/4
 */
public class newnew extends OpcodeSupport{
    @Override
    public int opcode(){
        return 187;
    }

    @Override
    public int operandNum(){
        return 2;
    }

    @Override
    public Object operate(StackFrame frame){

        ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, fetchOperand(frame, 2), ConstantClassInfo.class);

        ConstantUtf8Info classNameInfo = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);
        RTClass rtClass = RTMethodArea.loadClass(classNameInfo.string());

        RTObject rtObject = new RTObject();
        rtObject.setRtClass(rtClass);
        //初始化实例变量为默认值
        //执行初始化方法<init>
        RTHeap.register(rtObject);

        frame.getOperands().push(rtObject);
        return null;
    }
}
