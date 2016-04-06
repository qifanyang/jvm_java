package vm.parser.attribute;

import vm.parser.AttributeObject;
import vm.parser.U2;
import vm.parser.U4;

/**
 * @author yangqf
 * @version 1.0 2016/4/6
 */
public abstract class AttributeInfoSupport implements AttributeObject{

    U2 attribute_name_index;
    U4 attribute_length;

    public U2 getAttribute_name_index(){
        return attribute_name_index;
    }

    public void setAttribute_name_index(U2 attribute_name_index){
        this.attribute_name_index = attribute_name_index;
    }

    public U4 getAttribute_length(){
        return attribute_length;
    }

    public void setAttribute_length(U4 attribute_length){
        this.attribute_length = attribute_length;
    }
}
