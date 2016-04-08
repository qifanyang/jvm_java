package vm.opcode;

import vm.parser.ConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;
import vm.parser.cp.*;
import vm.runtime.RTClass;
import vm.runtime.StackFrame;
import vm.util.Pair;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public abstract class OpcodeSupport implements Opcode{

//    @Override
    public int operandNum(){
        return 0;
    }

    /**
     * 取出当前opcode的操作数
     * @param frame 当前栈帧
     * @param operandOffset 操作数偏移量,字节
     * @return
     */
    protected int fetchOperand(StackFrame frame, int operandOffset){
        int pc = frame.getPc();//goto pc实现跳转
//        int operandNum = operandNum();
        int operand = 0;
        if(1 == operandOffset){
            frame.setOperandOffset(frame.getOperandOffset()+1);//连续两次读取一个字节的操作数
            U1 b1 = frame.getCode()[pc+frame.getOperandOffset()];
            operand = b1.value;
//            frame.setPc(pc+1);
        }else if(2 == operandOffset){
            frame.setOperandOffset(frame.getOperandOffset()+2);
            U1 b1 = frame.getCode()[pc+1];
            U1 b2 = frame.getCode()[pc+2];
            operand = (((byte)b1.value) << 8)| ((byte)b2.value);
//            frame.setPc(pc+2);
        }else {
            throw new IllegalStateException("无法读取操作数, offset :"+operandOffset);
        }

        return operand;
    }

    protected <T> T indexConstantPoolObject(StackFrame frame, int index, Class<T> t){
        ConstantPoolObject constantPoolObject = frame.getConstantPool()[index].getConstantPoolObject();
        return (T) constantPoolObject;
    }

    protected <T> T indexConstantPoolObject(StackFrame frame, U2 index, Class<T> t){
        return indexConstantPoolObject(frame, index.value, t);
    }

    protected <T> T indexConstantPoolObject(RTClass rtClass, int index, Class<T> t){
        ConstantPoolObject constantPoolObject = rtClass.getClassFile().getConstant_pool_info()[index].getConstantPoolObject();
        return (T) constantPoolObject;
    }

    protected <T> T indexConstantPoolObject(RTClass rtClass, U2 index, Class<T> t){
        return indexConstantPoolObject(rtClass, index.value, t);
    }

   protected Pair<String , String> getMethodInfo(StackFrame frame, ConstantMethodRefInfo methodRefInfo){
       ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, methodRefInfo.getClass_index(), ConstantClassInfo.class);

       ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);

       //准备构建方法名和方法描述符
       ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, methodRefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
       String methodName = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class).string();
       String methodDescriptor = indexConstantPoolObject(frame, nameAndTypeInfo.getDescriptor_index(), ConstantUtf8Info.class).string();

       return new Pair<>(methodName, methodDescriptor);
   }

    protected Pair<String , String> getMethodInfo(StackFrame frame, ConstantInterfaceMethodRefInfo  methodRefInfo){
       ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, methodRefInfo.getClass_index(), ConstantClassInfo.class);

       ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);

       //准备构建方法名和方法描述符
       ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, methodRefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
       String methodName = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class).string();
       String methodDescriptor = indexConstantPoolObject(frame, nameAndTypeInfo.getDescriptor_index(), ConstantUtf8Info.class).string();

       return new Pair<>(methodName, methodDescriptor);
   }

}
