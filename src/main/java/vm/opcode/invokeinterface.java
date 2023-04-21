package vm.opcode;

import vm.parser.MethodInfo;
import vm.parser.cp.ConstantIntegerInfo;
import vm.parser.cp.ConstantInterfaceMethodRefInfo;
import vm.parser.cp.ConstantMethodRefInfo;
import vm.runtime.RTObject;
import vm.runtime.StackFrame;
import vm.util.Pair;

import java.util.LinkedList;

/**
 * 调用接口方法,
 * invokeinterface
 * indexbyte1
 * indexbyte2
 * count
 * 0
 * <p>
 * indexbyte1和indexbyte2用于构建常量池索引
 * <p>
 * 方法搜索过程:
 * 假如C是objectref指向的class,引用指向的对象包含类的Class的直接引用
 * 1.假如c包含名字和描述符都相同的实例方法,搜索终止,调用该方法
 * 2.递归查找C的直接父类,重复步骤1
 * 3.没有找到匹配的实例方法,抛出AbstractMethodError
 *
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class invokeinterface extends OpcodeSupport {
    @Override
    public int opcode() {
        return 185;
    }

    @Override
    public Object operate(StackFrame frame) {

        int operand = fetchOperand(frame, 2);
        int count = fetchOperand(frame, 1);
        int zero = fetchOperand(frame, 1);
        ConstantInterfaceMethodRefInfo interfaceMethodRefInfo = indexConstantPoolObject(frame, operand, ConstantInterfaceMethodRefInfo.class);
        Pair<String, String> pair = getMethodInfo(frame, interfaceMethodRefInfo);

        LinkedList<Object> paraList = new LinkedList<>();
        while (!frame.getOperands().isEmpty()) {
            paraList.addFirst(frame.getOperands().pop());
        }
        Object[] paraObjectsForCall = paraList.toArray(new Object[paraList.size()]);
        RTObject rtObject = (RTObject) paraList.getFirst();

        MethodInfo methodInfo = rtObject.getRtClass().searchRecursiveMethodInfo(pair.getFirst(), pair.getSecond());

        StackFrame newFrame = frame.getThreadStack().createStackFrame(methodInfo);
        //方法调用,填充参数到新栈帧
        //...objectref,x,y->
        for (int i = 0; i < newFrame.getLocals().length; i++) {
            newFrame.getLocals()[i] = paraObjectsForCall[i];
        }
        return null;
    }
}
