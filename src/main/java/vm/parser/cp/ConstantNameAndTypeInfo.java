package vm.parser.cp;

import vm.parser.*;

import java.io.IOException;

/**
 *用于表示字段和方法,由名字和描述构成,当然这里也只是存储指向utf8_info的索引
 * descriptor_index方法描述符格式:
 * Object m(int i, double d, Thread t) {...}
 * is:
 * (IDLjava/lang/Thread;)Ljava/lang/Object;
 * when resolve method call, jvm use name and descriptor match the real methodInfo, then intercept the byte code
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantNameAndTypeInfo extends ConstantPoolObject{
    U1 tag = U1.of(12);
    U2 name_index;//指向常量池的一个索引,在常量池中的数据类型为utf8_info
    U2 descriptor_index;//描述符,字段类型和方法描述符


    @Override
    public void parse(ClassFileReader reader) throws IOException {
        name_index = reader.readU2();
        descriptor_index = reader.readU2();
    }
}
