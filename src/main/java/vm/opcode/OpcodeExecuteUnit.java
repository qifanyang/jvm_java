package vm.opcode;

import vm.opcode.Opcode;
import vm.opcode.iconst_1;
import vm.opcode.iconst_2;
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
        register(istore_1.class);
        register(istore_2.class);
        register(istore_3.class);
        register(iload_1.class);
        register(iload_2.class);
        register(iadd.class);
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
        System.out.println("执行方法:"+frame.getMethodInfo());

        U1[] code = frame.getCode();

        for(U1 u1 : code){
            Opcode opcode = opcodeMap.get(u1.value);
            if(opcode == null){
                throw new IllegalStateException("非法字节码 , opcode = " + u1.value);
            }else{
                opcode.operateAndIncPC(frame);
            }

            System.out.println("opcode = " + opcode);
            frame.show();
        }

    }
}
