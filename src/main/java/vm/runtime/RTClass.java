package vm.runtime;

import vm.parser.*;
import vm.parser.cp.ConstantClassInfo;
import vm.parser.cp.ConstantUtf8Info;

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
public class RTClass{
    private ClassFile classFile;

    private Map<String, Object> staticFields = new HashMap<>();

    public void init(){
        //为静态字段分配空间,字段名字可以区分
        FieldInfo[] fields = classFile.getFields();
        for(FieldInfo fieldInfo : fields){
            U2 access_flags = fieldInfo.getAccess_flags();
            if((access_flags.value & 0x0008) != 0){//ACC_STATIC 0x0008 static，表示静态字段。
                ConstantPoolObject name = classFile.getConstant_pool_info()[fieldInfo.getName_index().value].getConstantPoolObject();
                ConstantPoolObject descriptor = classFile.getConstant_pool_info()[fieldInfo.getDescriptor_index().value].getConstantPoolObject();

                String descriptorStr = descriptor.toString();
                if(descriptorStr.equals("B")){
                    staticFields.put(name.toString(), Byte.valueOf((byte)0));
                }else if(descriptorStr.equals("I")){
                    staticFields.put(name.toString(), Integer.valueOf(0));
                }else if(descriptorStr.startsWith("L")){
                    //引用类型,//Ljava/io/PrintStream
                    String refClass = descriptorStr.substring(1);
                    RTClass rtClass = null;
                    try{
                         rtClass = RTMethodArea.loadClass(refClass);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //加载类,初始化静态引用字段, 这里需要创建对象
                    staticFields.put(name.toString(), rtClass);
                }else{
                    //TODO do more ....
                }
            }
        }
    }

    /**
     * 在当前{@link RTClass}中查找方法直接引用
     * @param methodName
     * @param methodDescriptor
     * @return
     */
    public MethodInfo searchMethodInfo(String methodName, String methodDescriptor){
        MethodInfo[] methods = classFile.getMethods();
        MethodInfo findMethodInfo = null;
        for(MethodInfo methodInfo : methods){
            ConstantUtf8Info nameInfo = (ConstantUtf8Info) classFile.getConstant_pool_info()[ methodInfo.getName_index().value].getConstantPoolObject();
            ConstantUtf8Info descriptorInfo = (ConstantUtf8Info) classFile.getConstant_pool_info()[methodInfo.getDescriptor_index().value].getConstantPoolObject();
            if(nameInfo.string().equals(methodName) && descriptorInfo.string().equals(methodDescriptor)){
                findMethodInfo = methodInfo;
                break;
            }
        }
        return findMethodInfo;
    }

    /**
     * 向上递归查找方法调用
     * @param methodName
     * @param methodDescriptor
     * @return
     */
    public MethodInfo searchRecursiveMethodInfo(String methodName, String methodDescriptor){

        MethodInfo methodInfo = searchMethodInfo(methodName, methodDescriptor);
        if(null == methodInfo){
            U2 super_class = this.classFile.getSuper_class();
            if(0 == super_class.value)return null;//递归停止条件为,没有父类了

            ConstantClassInfo superClassInfo = (ConstantClassInfo) this.classFile.getConstant_pool_info()[super_class.value].getConstantPoolObject();
            ConstantUtf8Info superClassNameInfo = (ConstantUtf8Info) this.classFile.getConstant_pool_info()[superClassInfo.getName_index().value].getConstantPoolObject();
            //直接父类
            RTClass rtClass = RTMethodArea.loadClass(superClassNameInfo.string());
            return rtClass.searchMethodInfo(methodName, methodDescriptor);
        }else {
            return methodInfo;
        }

    }
}
