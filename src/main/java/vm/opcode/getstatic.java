package vm.opcode;

import com.sun.org.apache.bcel.internal.classfile.ConstantFloat;
import vm.parser.IConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;
import vm.parser.cp.*;
import vm.runtime.ClassRT;
import vm.runtime.MethodArea;
import vm.runtime.StackFrame;

/**
 * Operation: Get static field from class
 * Format: getstatic indexbyte1 indexbyte2  , (indexbyte1 << 8) | indexbyte2)
 * 根据接下来的两个字节的操作数,计算出常量池索引位置,在常量池中的数据结构应该是一个
 * Fieldref,包含了名字和描述符,然后可以根据名字和描述符解析改字段,定位到该字段的直接
 * 引用,并将该值压入操作数栈中
 *
 * 类的静态字段在类加载时就可以在方法区中分配,所以在解析类的class的时候,应该分析类静态字段并
 * 分配
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class getstatic extends OpcodeSupport{
    @Override
    public int opcode(){
        return 178;
    }

    @Override
    public int operandNum(){
        return 2;
    }

    @Override
    public Object operate(StackFrame frame){
        //getstatic 有操作数, 紧接着的两个byte
//        int pc = frame.getThreadStack().getPc();//goto pc实现跳转
//        U1 b1 = frame.getCode()[pc];
//        U1 b2 = frame.getCode()[pc+1];
//        int index = (((byte)b1.value) << 8)| ((byte)b2.value);
        int index = fetchOperand(frame);
        //指向一个字段的符号引用
        ConstantPoolInfo constantPoolInfo = frame.getConstantPool()[index];

        ConstantFieldrefInfo fieldRefInfo = (ConstantFieldrefInfo) constantPoolInfo.getConstantPoolObject();

        //访问字段,首先需要解析类,将类的符号引用解析为直接引用
        U2 class_index = fieldRefInfo.getClass_index();
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) frame.getConstantPool()[class_index.value].getConstantPoolObject();
        IConstantPoolObject classNameObject = frame.getConstantPool()[constantClassInfo.getName_index().value].getConstantPoolObject();
        String className = classNameObject.toString();
        ClassRT classRT = MethodArea.findClass(className);//得到类运行时数据

        U2 name_and_type_index = fieldRefInfo.getName_and_type_index();
        IConstantPoolObject nameAndTypeObject = frame.getConstantPool()[name_and_type_index.value].getConstantPoolObject();
        ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo) nameAndTypeObject;
        IConstantPoolObject fieldNameObject = frame.getConstantPool()[nameAndTypeInfo.getName_index().value].getConstantPoolObject();
        Object fieldValue = classRT.getStaticFields().get(fieldNameObject.toString());//得到静态字段值

        frame.getOperands().push(fieldValue);

        //解析字段符号引用--->字段直接引用
        //解析class文件可以理解为将class文件转换为运行时数据,类字段也可以保存在classFile中
        //方法调用?自己新建java.lang.System,方法调用最终是执行字节码,
        //io输出最终要访问本地io,对应就是System.out

        return null;
    }
}
