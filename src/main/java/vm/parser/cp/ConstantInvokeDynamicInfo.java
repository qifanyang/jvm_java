package vm.parser.cp;

import vm.parser.*;

import java.io.IOException;

/**
 * jdk8使用该指令实现lambda, 使用指令invokedynamic, 索引item叫做 call site specifier
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantInvokeDynamicInfo extends ConstantPoolObject{
    U1 tag = U1.of(18);
    /**
     * the index of BootstrapMethods attribute in ClassFile
     * 需要解析对应的BootstrapMethod attribute
     */
    U2 bootstrap_method_attr_index;
    U2 name_and_type_index;


    @Override
    public void parse(ClassFileReader reader) throws IOException {
        bootstrap_method_attr_index = reader.readU2();
        name_and_type_index = reader.readU2();
    }
}
