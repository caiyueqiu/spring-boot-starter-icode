package com.icode.juc.memory;

import org.openjdk.jol.info.ClassLayout;

/**
 * 默认配置，启动了压缩指针，-XX:+UseCompressedClassPointers
 * 12 + 4（对齐填充） = 一个对象16字节
 * 手动配置，关闭了压缩指，-XX:-UseCompressedClassPointers
 * 8 + 8 = 一个对象16字节
 *
 * @author caiyq
 * @date 2022/6/5 8:55
 */
public class JolDemo {

    public static void main(String[] args) throws Exception {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Consumer c1 = new Consumer();
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
    }
}

class Consumer {
    // 只有对象头，没有其它任何实例数据
    // int + boolean，默认满足对其填充，24 bytes
    int id;
    boolean flag = false;
}
