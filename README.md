# jvm_java

### 简介

该项目用于学习字节码,深入了解java语言运行机制,不停留在空洞的概念,也不深入到底层实现,  
点到为止,能解惑工作中的疑问即可,通过该项目可掌握

- 字节码静态结构
- 字节码运行时
- java新语法底层实现
- ...

***最核心的是对jvm指令执行更加印象深刻***

    public class iload_3 extends OpcodeSupport {
        @Override
        public int opcode() {
            return 29;
        }
    
        @Override
        public Object operate(StackFrame frame) {
            //指令具体行为,代码自解释,看指令描述很是枯燥
            frame.getOperands().push(frame.getLocals()[3]);
            return null;
        }
    }
    
### 使用示例

运行单测**junit.QuickTest.staticFuncTest**

    public class QuickTest {
    
        @Test
        public void staticFuncTest() throws Exception{
            VirtualMachine machine = new VirtualMachine();
            //执行类需要包含main方法
            machine.run("source.StaticFunc");
        }
    }

控制台输出结果

### 项目目录

- src/main/java/vm/lib: jdk库解析工作量大,作为学习使用应保持KISS原则,在字节码测试代码中不使用jdk类库,使用lib中的类替代jdk的类库.
- src/main/java/vm/opcode: jvm指令集(100+),详细介绍见Oracle官网,地址见文末
- src/main/java/vm/parser: class数据结构以及解析
- src/main/java/vm/runtime: 模拟vm运行时,堆,栈,Class,对象,栈帧等
- src/main/java/vm/util: 工具类,方便调试
- src/main/java/source: 调试用的源代码,等同于应用代码,vm会加载源码对应的class

- src/test/java/junit: 测试用例
-

### 资料
- java各个版本语言和虚拟机规范,包含html和pdf格式:https://docs.oracle.com/javase/specs/index.html
- 字节码查看工具,可替代javap命令:https://github.com/ingokegel/jclasslib

