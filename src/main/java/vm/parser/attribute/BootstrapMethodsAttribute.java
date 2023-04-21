package vm.parser.attribute;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vm.parser.ClassFileReader;
import vm.parser.Parser;
import vm.parser.U2;
import vm.parser.U4;

import java.io.IOException;

/**
 * invokedynamic指令数量越多,这个属性长度就越长
 *
 * @author yangqf
 * @version 1.0 2016/10/31
 */
@Setter
@Getter
public class BootstrapMethodsAttribute extends AttributeInfoSupport {
    //    U2 attribute_name_index;
//    U4 attribute_length;
    U2 num_bootstrap_methods;//bootstrap method 数量
    BootstrapMethod[] bootstrapMethods;

    @Data
    @NoArgsConstructor
    public static class BootstrapMethod implements Parser {
        /**
         * index constant pool item is CONSTANT_MethodHandle_info
         * 更加通用的方法引用,用来完成方法调用,赋值等
         */
        U2 bootstrap_method_ref;
        U2 num_bootstrap_arguments;
        /**
         * index constant pool item is CONSTANT_String_info, CONSTANT_Integer_info ... and so on
         */
        U2 bootstrap_arguments[];

        @Override
        public void parse(ClassFileReader reader) throws IOException {
            this.bootstrap_method_ref = reader.readU2();
            this.num_bootstrap_arguments = reader.readU2();
            this.bootstrap_arguments = new U2[num_bootstrap_arguments.value];
            for (int j = 0; j < this.num_bootstrap_arguments.value; j++) {
                this.bootstrap_arguments[j] = reader.readU2();
            }
        }
    }

    //structure
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 num_bootstrap_methods;
//    { u2 bootstrap_method_ref;
//        u2 num_bootstrap_arguments;
//        u2 bootstrap_arguments[num_bootstrap_arguments];
//    } bootstrap_methods[num_bootstrap_methods];
    @Override
    public void parse(ClassFileReader reader) throws IOException {
        num_bootstrap_methods = reader.readU2();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods.value];
        for (int i = 0; i < num_bootstrap_methods.value; i++) {
            bootstrapMethods[i] = new BootstrapMethod();
            bootstrapMethods[i].parse(reader);
        }
    }
}
