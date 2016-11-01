package vm.util;

import vm.parser.AttributeInfo;
import vm.parser.ClassFile;

/**
 * @author yangqf
 * @version 1.0 2016/11/1
 */
public class AttributeUtil{

    public static <T> T getAttribute(ClassFile classFile,String attriName, Class<T> t){
        AttributeInfo[] attributes = classFile.getAttributes();
        for(AttributeInfo attributeInfo : attributes){
            int i = attributeInfo.getAttribute_name_index().value;
            if(classFile.getConstant_pool_info()[i].getConstantPoolObject().toString().equals(attriName)){
                return (T) attributeInfo.getAttributes().get(0);
            }
        }
        return null;
    }
}
