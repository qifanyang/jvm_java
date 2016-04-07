package vm.runtime;

import vm.opcode.*;
import vm.parser.U1;
import vm.runtime.StackFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * 字节码执行
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class OpcodeExecuteUnit{

    private static Map<Integer, Opcode> opcodeMap = new HashMap<>();

    //手动注册,改为扫描包
    static {
        register(iconst_m1.class);
        register(iconst_0.class);
        register(iconst_1.class);
        register(iconst_2.class);
        register(iconst_3.class);
        register(iconst_4.class);
        register(iconst_5.class);
        register(istore.class);
        register(istore_0.class);
        register(istore_1.class);
        register(istore_2.class);
        register(istore_3.class);
        register(iload.class);
        register(iload_0.class);
        register(iload_1.class);
        register(iload_2.class);
        register(iload_3.class);
        register(iadd.class);
        register(bipush.class);
        register(getstatic.class);
        register(putfield.class);
        register(newnew.class);
        register(invokevirtual.class);
        register(invokespecial.class);
        register(invokestatic.class);
        register(returnreturn.class);
        register(ireturn.class);
        register(aload.class);
        register(aload_0.class);
        register(aload_1.class);
        register(aload_2.class);
        register(aload_3.class);
        register(astore.class);
        register(astore_0.class);
        register(astore_1.class);
        register(astore_2.class);
        register(astore_3.class);
        register(dup.class);
        register(ldc.class);
        register(if_icmpne.class);
        register(if_icmpeq.class);
        register(if_icmpgt.class);
        register(if_icmpge.class);
        register(if_icmplt.class);
        register(if_icmple.class);
        register(iinc.class);
        register(gotogoto.class);

        System.out.println("完成字节码指令数量 :" + opcodeMap.size());
    }

    public static void register(Class<? extends Opcode> opcode){
        try{
            Opcode oo = opcode.newInstance();
            opcodeMap.put(oo.opcode(), oo);
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }

    }

    public static void execute(StackFrame frame){
//        System.out.println("执行方法:"+frame.getMethodInfo());

        U1[] code = frame.getCode();
        int pc = frame.getThreadStack().getPc();
        //这里要完成执行环境切换
        while(pc < code.length){
            U1 u1 = code[pc];
            Opcode opcode = opcodeMap.get(u1.value);
            if(opcode == null){
                throw new IllegalStateException("非法字节码 , opcode = " + u1.value);
            }else{
                //执行指令前先修改pc到下一指令,
                //对于有操作数的opcode,在读取操作数的时候修改pc
                //operate中有跳转指令,会直接修改pc值到目标pc offset,所以不能再operate方法之后修改pc
                frame.setCurrentOpcode(opcode);
                opcode.operate(frame);
            }
            if(frame.getJumpOffset() == 0){
                //没有跳转
                ++pc;//指令自增
                frame.setPc(pc + opcode.getOperandLength());
            }else {
                frame.setPc(pc + frame.getJumpOffset());
                frame.setJumpOffset(0);
            }
            //检查当前帧是否切换,发生方法调用,当前帧改变,pc改变
            if(frame != frame.getThreadStack().getCurrentFrame()){
                code = frame.getThreadStack().getCurrentFrame().getCode();
                pc = frame.getThreadStack().getCurrentFrame().getPc();
                frame.getThreadStack().setPc(pc);
                frame = frame.getThreadStack().getCurrentFrame();
            }else {
                pc = frame.getPc();
            }
            //调试
//            System.out.println("opcode = " + opcode);
//            frame.show();


        }

    }
}
