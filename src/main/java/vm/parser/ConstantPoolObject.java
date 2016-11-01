package vm.parser;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 常量池对象接口
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@Data
@NoArgsConstructor
public abstract class ConstantPoolObject implements Parser{

    private ClassFile cf;

    protected abstract U1 getTag();

    /**
     * 因为常量池存储的是符号引用,类加载阶段会将部分符号引用转为直接引用,另一部分
     * 符号引用会在使用时转换为直接引用
     * @return
     */
//    public String string();

}
