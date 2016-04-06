package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.ConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;

import java.io.IOException;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantClassInfo implements ConstantPoolObject{
    U1 tag = U1.of(7);//在cp_info中已经有tag, 所以这里的tag只可以确定
    U2 name_index;//常量池索引,指向constant_utf8_info

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        name_index = reader.readU2();
    }


    public String string(){
        return null;
    }
}
