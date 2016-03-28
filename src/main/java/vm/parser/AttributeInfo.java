package vm.parser;

import vm.parser.attribute.CodeAttribute;
import vm.parser.attribute.LineNumberTableAttribute;
import vm.parser.attribute.LocalVariableTableAttribute;
import vm.parser.attribute.SourceFileAttribute;
import vm.parser.cp.ConstantPoolInfo;
import vm.parser.cp.ConstantUtf8Info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 属性,种类有很多,比如code,InnerClasses, 在ClassFile,Field_info,method_info,和code_attribute都有使用
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class AttributeInfo {
    U2 attribute_name_index;
    U2 attribute_length;
    U1 info[];//这里类似常量池,需要根据attribute_name_index的值判断是那种attribute

    List<IAttributeObject> attributes = new ArrayList<>();
    ClassFile cf;
    public void parse(ClassFileReader reader) throws IOException {
        attribute_name_index = reader.readU2();
        attribute_length = reader.readU2();

        //该处常量池必须是constant_utf8_info结构
        ConstantPoolInfo constantPoolInfo = cf.getConstant_pool_info()[attribute_name_index.value];
        IConstantPoolObject constantPoolObject = constantPoolInfo.getConstantPoolObject();

        if(constantPoolInfo.getTag().value != 1){
            //jvm规定必须是constant_utf8_info
            throw new IllegalStateException("constant pool object must be a constant_utf8_info");
        }
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantPoolObject;
        U1[] bytes = utf8Info.getBytes();
        //TODO 这里强转byte, 估计有问题...
        byte[] byteBytes = new byte[bytes.length];
        for(int i = 0; i < bytes.length; i++){
            byteBytes[i] = (byte) bytes[i].value;
        }
        String attributeName = new String(byteBytes, "utf-8");

        IAttributeObject attributeObject = null;
        switch (attributeName){
            case "Code":
                attributeObject = new CodeAttribute();
                break;
            case "LineNumberTable":
                attributeObject = new LineNumberTableAttribute();
                break;
            case "LocalVariableTable":
                attributeObject = new LocalVariableTableAttribute();
                break;
            case "SourceFile":
                attributeObject = new SourceFileAttribute();
                break;
            default:
                throw new IllegalStateException("不能处理的属性类型  :" + attributeName);
        }

        attributeObject.parse(reader);
        attributes.add(attributeObject);

        info = new U1[attribute_length.value];
//        dataInput.readFully(info);
    }

}
