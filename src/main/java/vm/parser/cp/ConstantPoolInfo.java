package vm.parser.cp;

import vm.parser.ConstantPoolObject;
import vm.parser.U1;

/**
 *常量池对象包装
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class ConstantPoolInfo {
    /**
     * tag值表名结构体类型
     */
    U1 tag;
    /**
     * 根据tag结合对应的常量池数据结构可以读取
     */
    U1 info[];
    /**
     * 解析info[]出来的数据结构,{@link ConstantPoolObject}是所有常量池结构的父接口
     **/
    ConstantPoolObject constantPoolObject;

}
