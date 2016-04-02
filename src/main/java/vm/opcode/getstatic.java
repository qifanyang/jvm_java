package vm.opcode;

import com.sun.org.apache.bcel.internal.classfile.ConstantFloat;
import vm.parser.IConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;
import vm.parser.cp.ConstantClassInfo;
import vm.parser.cp.ConstantFieldrefInfo;
import vm.parser.cp.ConstantPoolInfo;
import vm.parser.cp.ConstantUtf8Info;
import vm.runtime.StackFrame;

/**
 * Operation: Get static field from class
 * Format: getstatic indexbyte1 indexbyte2  , (indexbyte1 << 8) | indexbyte2)
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class getstatic extends OpcodeSupport{
    @Override
    public int opcode(){
        return 178;
    }

    @Override
    public Object operate(StackFrame frame){
        //getstatic 有操作数, 紧接着的两个byte
        int pc = frame.getThreadStack().getPc();//goto pc实现跳转
        U1 b1 = frame.getCode()[pc];
        U1 b2 = frame.getCode()[pc+1];
        int index = (((byte)b1.value) << 8)| ((byte)b2.value);
        //指向一个字段的符号引用
        ConstantPoolInfo constantPoolInfo = frame.getConstantPool()[index];

        ConstantFieldrefInfo fieldRefInfo = (ConstantFieldrefInfo) constantPoolInfo.getConstantPoolObject();

        //访问字段,首先需要解析类,将类的符号引用解析为直接引用
        U2 class_index = fieldRefInfo.getClass_index();
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) frame.getConstantPool()[class_index.value].getConstantPoolObject();

        //解析字段符号引用--->字段直接引用

        return null;
    }
}
