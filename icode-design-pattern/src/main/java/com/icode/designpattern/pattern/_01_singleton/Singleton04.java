package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 懒汉式（线程安全，同步方法）
 *
 * @author caiyq
 * @date 2022/2/20 22:30
 */
public class Singleton04 {
    // 构造器私有化
    private Singleton04() {
    }

    // 内部创建对象实例
    private static Singleton04 SINGLETON;

    // 提供一个静态的公有方法，当使用到该方法时，才去创建实例，加入同步处理，解决线程安全问题
    public static synchronized Singleton04 getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new Singleton04();
        }
        return SINGLETON;
    }
}
