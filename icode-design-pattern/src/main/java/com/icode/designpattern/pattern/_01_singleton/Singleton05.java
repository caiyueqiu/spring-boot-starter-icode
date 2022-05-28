package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 懒汉式（线程安全，同步代码块）
 *
 * @author caiyq
 * @date 2022/2/20 22:30
 */
public class Singleton05 {
    // 构造器私有化
    private Singleton05() {
    }

    // 内部创建对象实例
    private static Singleton05 SINGLETON;

    // 提供一个静态的公有方法，当使用到该方法时，才去创建实例，加入同步处理，解决线程安全问题
    public static Singleton05 getInstance() {
        if (SINGLETON == null) {
            synchronized (Singleton05.class) {
                SINGLETON = new Singleton05();
            }
        }
        return SINGLETON;
    }
}
