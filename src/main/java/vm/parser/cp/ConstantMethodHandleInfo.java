package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.ConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;

import java.io.IOException;

/**
 * 表示方法句柄,
 * 如果kind为访问字段,那么index为指向constant_field_ref_info的索引
 * 如果kind为调用方法,那么index为指向constant_method_ref_info的索引
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantMethodHandleInfo implements ConstantPoolObject{
    U1 tag = U1.of(15);
    U1 reference_kind;
    U2 reference_index;

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        reference_kind = reader.readU1();
        reference_index = reader.readU2();
    }
}
