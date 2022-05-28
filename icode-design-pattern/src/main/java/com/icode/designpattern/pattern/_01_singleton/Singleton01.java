package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 饿汉式（静态常量）
 *
 * @author caiyq
 * @date 2022/2/20 22:19
 */
public class Singleton01 {
    // 构造器私有化
    private Singleton01() {}

    // 内部创建对象实例
    private final static Singleton01 SINGLETON = new Singleton01();

    // 对外提供一个公共的静态方法，返回实例对象
    public static Singleton01 getInstance() {
        return SINGLETON;
    }
}
