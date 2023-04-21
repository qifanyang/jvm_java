package vm.opcode;

import vm.parser.cp.*;
import vm.runtime.RTClass;
import vm.runtime.RTMethodArea;
import vm.runtime.StackFrame;

/**
 * Operation: Get static field from class
 * Format: getstatic indexbyte1 indexbyte2  , (indexbyte1 << 8) | indexbyte2)
 * 根据接下来的两个字节的操作数,计算出常量池索引位置,在常量池中的数据结构应该是一个
 * Fieldref,包含了名字和描述符,然后可以根据名字和描述符解析改字段,定位到该字段的直接
 * 引用,并将该值压入操作数栈中
 * <p>
 * 类的静态字段在类加载时就可以在方法区中分配,所以在解析类的class的时候,应该分析类静态字段并
 * 分配
 *
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class getstatic extends OpcodeSupport {
    @Override
    public int opcode() {
        return 178;
    }

    @Override
    public int operandNum() {
        return 2;
    }

    @Override
    public Object operate(StackFrame frame) {
        //getstatic 有操作数, 紧接着的两个byte
//        int pc = frame.getThreadStack().getPc();//goto pc实现跳转
//        U1 b1 = frame.getCode()[pc];
//        U1 b2 = frame.getCode()[pc+1];
//        int index = (((byte)b1.value) << 8)| ((byte)b2.value);
        int index = fetchOperand(frame, 2);

        //指向一个静态字段的符号引用
        ConstantFieldrefInfo fieldRefInfo = indexConstantPoolObject(frame, index, ConstantFieldrefInfo.class);

        //访问字段,首先需要解析类,将类的符号引用解析为直接引用
        ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, fieldRefInfo.getClass_index(), ConstantClassInfo.class);
        ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);

        String className = classNameUtf8Info.string();
        RTClass rtClass = RTMethodArea.loadClass(className);//加载System
//        if(null == rtClass){//静态字段所属于的类没有加载,这里加载
//            rtClass = RTMethodArea.loadClass(className);//加载System
//        }

        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, fieldRefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
        ConstantUtf8Info fieldNameUtf8Info = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class);

        Object fieldValue = rtClass.getStaticFields().get(fieldNameUtf8Info.string());//得到静态字段值

        frame.getOperands().push(fieldValue);

        //解析字段符号引用--->字段直接引用
        //解析class文件可以理解为将class文件转换为运行时数据,类字段也可以保存在classFile中
        //方法调用?自己新建java.lang.System,方法调用最终是执行字节码,
        //io输出最终要访问本地io,对应就是System.out

        return null;
    }
}
