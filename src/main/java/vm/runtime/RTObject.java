package vm.runtime;

import java.util.HashMap;
import java.util.Map;

/**
 * 运行时对象, 通过new指令创建, 放在{@link RTHeap}中
 * @author yangqf
 * @version 1.0 2016/4/4
 */
@lombok.Setter
@lombok.Getter
public class RTObject{
    private RTClass rtClass;
    private Map<String, Object> insVariableMap = new HashMap<>();
}
