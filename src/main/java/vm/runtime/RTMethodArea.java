package vm.runtime;

import vm.parser.ClassFile;
import vm.parser.ClassFileParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法区,包含类运行时数据
 * @author yangqf
 * @version 1.0 2016/4/1
 */
public class RTMethodArea{

    private static Map<String, RTClass> classRTMap = new HashMap<>();


    public static void register(String key, RTClass classRT){
        classRTMap.put(key, classRT);
    }

    public static RTClass findClass(String key){
        return classRTMap.get(key);
    }

    public static RTClass loadClass(String name){
        if(findClass(name) != null){
            return findClass(name);
        }
        String name1 = name.replace(".", "/");
        ClassFile classFile = ClassFileParser.load(name1);
        RTClass classRT = new RTClass();
        classRT.setClassFile(classFile);
        classRT.init();
        register(name1, classRT);
        return classRT;
    }

}
