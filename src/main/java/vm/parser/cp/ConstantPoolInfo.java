package vm.parser.cp;

import vm.parser.IConstantPoolObject;
import vm.parser.U1;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class ConstantPoolInfo {
    U1 tag;
    U1 info[];
    IConstantPoolObject constantPoolObject;

}
