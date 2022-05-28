package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 静态内部类
 *
 * @author caiyq
 * @date 2022/2/20 22:30
 */
public class Singleton07 {
    // 构造器私有化
    private Singleton07() {
    }

    // 内部创建对象实例
    private static volatile Singleton07 SINGLETON;

    // 静态内部类，该类中有一个静态属性
    private static class Singleton {
        private static final Singleton07 INSTANCE = new Singleton07();
    }

    // 提供一个静态的公有方法，直接返回Singleton.INSTANCE
    public static synchronized Singleton07 getInstance() {
        return Singleton.INSTANCE;
    }
}
