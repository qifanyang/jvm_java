package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.IConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;

import java.io.IOException;

/**
 *用于表示字段和方法,由名字和描述构成,当然这里也只是存储指向utf8_info的索引
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class ConstantNameAndTypeInfo implements IConstantPoolObject {
    U1 tag = U1.of(12);
    U2 name_index;//指向常量池的一个索引,在常量池中的数据类型为utf8_info
    U2 descriptor_index;//描述符,字段类型和方法描述符


    @Override
    public void parse(ClassFileReader reader) throws IOException {
        name_index = reader.readU2();
        descriptor_index = reader.readU2();
    }
}
