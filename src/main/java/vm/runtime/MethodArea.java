package vm.runtime;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法区,包含类运行时数据
 * @author yangqf
 * @version 1.0 2016/4/1
 */
public class MethodArea{

    private static Map<String, ClassRT> classRTMap = new HashMap<>();


    public static void register(String key, ClassRT classRT){
        classRTMap.put(key, classRT);
    }

    public static ClassRT findClass(String key){
        return classRTMap.get(key);
    }

}
