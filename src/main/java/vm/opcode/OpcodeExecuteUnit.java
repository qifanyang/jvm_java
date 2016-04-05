package vm.opcode;

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
        register(iconst_1.class);
        register(iconst_2.class);
        register(iconst_3.class);
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
        register(getstatic.class);
        register(putfield.class);
        register(newnew.class);
        register(invokevirtual.class);
        register(invokespecial.class);
        register(invokestatic.class);
        register(returnreturn.class);
        register(ireturn.class);
        register(aload.class);
        register(aload_3.class);
        register(astore.class);
        register(astore_3.class);
        register(dup.class);
        register(ldc.class);

//        System.out.println("完成字节码指令数量 :" + opcodeMap.size());
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
                opcode.operate(frame);
            }
            //对于有操作数的opcode,在operate方法中修改pc值
            pc = frame.getThreadStack().getPc()+1;
            frame.getThreadStack().setPc(pc);
            frame.setPc(pc);

            //检查当前帧是否切换,发生方法调用,当前帧改变,pc改变
            if(frame != frame.getThreadStack().getCurrentFrame()){
                code = frame.getThreadStack().getCurrentFrame().getCode();
                pc = frame.getThreadStack().getCurrentFrame().getPc();
                frame.getThreadStack().setPc(pc);
                frame = frame.getThreadStack().getCurrentFrame();
            }
            //调试
//            System.out.println("opcode = " + opcode);
//            frame.show();


        }

    }
}
