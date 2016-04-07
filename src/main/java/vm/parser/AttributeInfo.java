package vm.parser;

import vm.parser.attribute.*;
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
    U4 attribute_length;
    U1 info[];//这里类似常量池,需要根据attribute_name_index的值判断是那种attribute

    List<AttributeObject> attributes = new ArrayList<>();
    ClassFile cf;
    boolean isTop = true;//属性包含属性
    public void parse(ClassFileReader reader) throws IOException {
        attribute_name_index = reader.readU2();
        attribute_length = reader.readU4();
        reader.mark();

        //该处常量池必须是constant_utf8_info结构
        ConstantPoolInfo constantPoolInfo = cf.getConstant_pool_info()[attribute_name_index.value];
        ConstantPoolObject constantPoolObject = constantPoolInfo.getConstantPoolObject();

        if(constantPoolInfo.getTag().value != 1){
            //jvm规定必须是constant_utf8_info
            throw new IllegalStateException("constant pool object must be a constant_utf8_info");
        }

        ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantPoolObject;
//        U1[] bytes = utf8Info.getBytes();
//        byte[] byteBytes = new byte[bytes.length];
//        for(int i = 0; i < bytes.length; i++){
//            byteBytes[i] = (byte) bytes[i].value;
//        }
        String attributeName = utf8Info.string();

        AttributeInfoSupport attributeObject = null;
        switch (attributeName){
            case "Code":
                attributeObject = new CodeAttribute(cf);
                break;
            case "LineNumberTable":
                attributeObject = new LineNumberTableAttribute(cf);
                break;
            case "LocalVariableTable":
                attributeObject = new LocalVariableTableAttribute(cf);
                break;
            case "SourceFile":
                attributeObject = new SourceFileAttribute(cf);
                break;
            case "StackMapTable":
                attributeObject = new StackMapTableAttribute();
                break;
            case "Signature":
                attributeObject = new SignatureAttribute();
                break;
            case "Exceptions":
                attributeObject = new ExceptionsAttribute();
                break;
            default:
                throw new IllegalStateException("不能处理的属性类型  :" + attributeName);
        }
        attributeObject.setAttribute_name_index(attribute_name_index);
        attributeObject.setAttribute_length(attribute_length);
        attributeObject.parse(reader);
        attributes.add(attributeObject);

//        int remaining = attribute_length.value - (reader.position() - reader.markValue());
//        if(remaining > 0){
//            System.out.println("还有没读取完毕的 attribute , remaining = " + remaining);
//        }

    }

}
