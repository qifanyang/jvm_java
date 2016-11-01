package vm.opcode;

import vm.parser.attribute.BootstrapMethodsAttribute;
import vm.parser.cp.ConstantInvokeDynamicInfo;
import vm.parser.cp.ConstantMethodHandleInfo;
import vm.parser.cp.ConstantMethodRefInfo;
import vm.parser.cp.ConstantNameAndTypeInfo;
import vm.runtime.StackFrame;
import vm.util.AttributeUtil;

/**
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokedynamic
 * 使用该指令的地方叫做动态调用点,lambda表达式采用该指令实现
 *
 * @author yangqf
 * @version 1.0 2016/10/31
 */
public class invokedynamic extends OpcodeSupport{
    @Override
    public int opcode(){
        return 186;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);
        fetchOperand(frame, 2);//第三个,第四个字节awayls equals zero, skip it
        ConstantInvokeDynamicInfo constantInvokeDynamicInfo = indexConstantPoolObject(frame, operand, ConstantInvokeDynamicInfo.class);
        //ConstantInvokeDynamic包含bootstrapMethod

        //bootstrap method
        ConstantNameAndTypeInfo constantNameAndTypeInfo = indexConstantPoolObject(frame, constantInvokeDynamicInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);

        //解析调用
        //The call site specifier is resolved (§5.4.3.6) for this specific dynamic call site to obtain a reference to
        // a java.lang.invoke.MethodHandle instance that will serve as the bootstrap method,
        // a reference to a java.lang.invoke.MethodType instance,
        // and references to static arguments.
        BootstrapMethodsAttribute bootstrapMethods = AttributeUtil.getAttribute(frame.getMethodInfo().getCf(), "BootstrapMethods", BootstrapMethodsAttribute.class);
        BootstrapMethodsAttribute.BootstrapMethod bootstrapMethod = bootstrapMethods.getBootstrapMethods()[constantInvokeDynamicInfo.getBootstrap_method_attr_index().value];
        //类似构建MethodHandle和MethodType对象,拿到ConstantMethodHandle和参数

        //类似java中的MethodHandle
        ConstantMethodHandleInfo constantMethodHandleInfo = indexConstantPoolObject(frame, bootstrapMethod.getBootstrap_method_ref(), ConstantMethodHandleInfo.class);
        //reference_kind=6(REF_invokeStatic),the reference_index(CONSTANT_Methodref_info)
        if(constantMethodHandleInfo.getReference_index().value == 6){
            //bootstrapMethod指向的方法
            ConstantMethodRefInfo constantMethodRefInfo = indexConstantPoolObject(frame, constantMethodHandleInfo.getReference_index(), ConstantMethodRefInfo.class);
            //class_index 51 java/lang/invoke/LambdaMetafactory
            //name_and_type_index 52 metafactory (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        }
        //执行方法调用,返回CallSite

        //获取bootstrapMethod方法引用
        //bootstrap_method_ref=27又是一个methodHandle ref_kind=6 ref_index=40
        //method_ref class_index=51 java/lang/invoke/LambdaMetafactory
        //name_and_type_index=52 name_index=55 metafactory (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;

        //bootstrap_arguments
        //28 MethodType ()V
        //29 MethodHandle kind=6 MethodRef class_index=7(source/Lambda) name_and_type=53(lambda$main$0 ()V)
        //28 MethodType ()V
        //在bootstrap_arguments中完成对
        //根据methodHandle 信息执行方法调用
        System.out.println("");
        return null;
    }
}
