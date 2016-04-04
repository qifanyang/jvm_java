package vm.runtime;

import java.util.HashMap;
import java.util.Map;

/**
 * 堆, java对象在这里分配
 * @author yangqf
 * @version 1.0 2016/4/4
 */
@lombok.Getter
public class RTHeap{
    private static Map<Integer, RTObject> rtObjectMap = new HashMap<>();

    public static void register(RTObject rtObject){
        rtObjectMap.put(rtObject.hashCode(), rtObject);
    }
}
