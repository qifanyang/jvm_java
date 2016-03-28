package vm.parser;

import vm.parser.cp.ConstantPoolInfo;
import vm.parser.cp.ConstantUtf8Info;

import java.io.IOException;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class AttributeInfo {
    U2 attribute_name_index;
    U2 attribute_length;
    U1 info[];//这里类似常量池,需要根据attribute_name_index的值判断是那种attribute

    ClassFile cf;
    public void parse(ClassFileReader reader) throws IOException {
        attribute_name_index = reader.readU2();
        attribute_length = reader.readU2();

        ConstantPoolInfo constantPoolInfo = cf.getConstant_pool_info()[attribute_name_index.value];
        IConstantPoolObject constantPoolObject = constantPoolInfo.getConstantPoolObject();
        if(constantPoolInfo.getTag().value == 1){
            ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantPoolObject;
//            String str = new String(utf8Info.getBytes(), "utf-8");
//            System.out.println(str);
        }
        info = new U1[attribute_length.value];
//        dataInput.readFully(info);
    }

}
