package vm.runtime;

import vm.parser.ClassFile;
import vm.parser.FieldInfo;
import vm.parser.IConstantPoolObject;
import vm.parser.U2;

import java.util.HashMap;
import java.util.Map;

/**
 * class运行时数据,为了让ClassFile保持简洁,不在其中加入其它字段,
 * RT is short for RunTime, 包含{@link vm.parser.ClassFile}
 * @author yangqf
 * @version 1.0 2016/4/4
 */
@lombok.Setter
@lombok.Getter
public class ClassRT{
    private ClassFile classFile;

    private Map<String, Object> staticFields = new HashMap<>();

    public void init(){
        //为静态字段分配空间,字段名字可以区分
        FieldInfo[] fields = classFile.getFields();
        for(FieldInfo fieldInfo : fields){
            U2 access_flags = fieldInfo.getAccess_flags();
            if((access_flags.value & 0x0008) != 0){//ACC_STATIC 0x0008 static，表示静态字段。
                IConstantPoolObject name = classFile.getConstant_pool_info()[fieldInfo.getName_index().value].getConstantPoolObject();
                IConstantPoolObject descriptor = classFile.getConstant_pool_info()[fieldInfo.getDescriptor_index().value].getConstantPoolObject();

                String descriptorStr = descriptor.toString();
                if(descriptor.equals("B")){
                    staticFields.put(name.toString(), Byte.valueOf((byte)0));
                }else if(descriptor.equals("I")){
                    staticFields.put(name.toString(), Integer.valueOf(0));
                }else{
                    //TODO do more ....
                }
            }
        }
    }

}
