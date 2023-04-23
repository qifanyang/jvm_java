## class解析详解

#### classFile结构
    ClassFile {
        u4 magic;
        u2 minor_version;
        u2 major_version;
        u2 constant_pool_count;
        cp_info constant_pool[constant_pool_count-1];
        u2 access_flags;
        u2 this_class;
        u2 super_class;
        u2 interfaces_count;
        u2 interfaces[interfaces_count];
        u2 fields_count;
        field_info fields[fields_count];
        u2 methods_count;
        method_info methods[methods_count];
        u2 attributes_count;
        attribute_info attributes[attributes_count];
    }
- 描述符: 使用string表示字段和方法类型, 比如:
  - I->int, D->double, L->reference, [->array
  - (IDLjava/lang/Thread;)Ljava/lang/Object;->Object m(int i, double d, Thread t) {...}
- 常量池: jvm指令不依赖运行时的class,字节码中使用符号引用,符号引用的值加载class通过查询常量池转换
  cp_info {
     u1 tag;
     u1 info[];
  }
- 