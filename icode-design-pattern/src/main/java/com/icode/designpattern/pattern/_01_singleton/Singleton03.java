package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 懒汉式（线程不安全）
 *
 * @author caiyq
 * @date 2022/2/20 22:30
 */
public class Singleton03 {
    // 构造器私有化
    private Singleton03() {}

    // 内部创建对象实例
    private static Singleton03 SINGLETON;

    // 提供一个静态的公有方法，当使用到该方法时，才去创建实例
    public static Singleton03 getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new Singleton03();
        }
        return SINGLETON;
    }
}
