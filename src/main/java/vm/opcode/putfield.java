package vm.opcode;

import vm.runtime.StackFrame;

/**
 * putfield      #4                  // Field t:LTest;
 * 给当前实例对象属性设值,操作数索引指向的常量池数据结构为fieldref,
 * 根据属性的描述符确定如何设值,
 * 如果是基本类型byte,boolean,short,char设值为int
 * 如果是引用类型,
 *
 * @author yangqf
 * @version 1.0 2016/4/4
 */
public class putfield extends OpcodeSupport{
    @Override
    public int opcode(){
        return 0;
    }

    @Override
    public Object operate(StackFrame frame){
        return null;
    }
}
