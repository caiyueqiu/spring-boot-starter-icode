package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 饿汉式（静态代码块）
 *
 * @author caiyq
 * @date 2022/2/20 22:30
 */
public class Singleton02 {
    // 构造器私有化
    private Singleton02() {}

    // 内部创建对象实例
    private static Singleton02 SINGLETON;

    static {
        SINGLETON = new Singleton02();
    }

    // 对外提供一个公共的方法，返回对象实例
    public static Singleton02 getInstance() {
        return SINGLETON;
    }
}
