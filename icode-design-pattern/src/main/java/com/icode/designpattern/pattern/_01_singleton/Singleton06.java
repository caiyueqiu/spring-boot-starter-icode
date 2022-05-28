package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 双重检查（线程安全，延迟加载，效率较高）
 *
 * @author caiyq
 * @date 2022/2/20 22:30
 */
public class Singleton06 {
    // 构造器私有化
    private Singleton06() {
    }

    // 内部创建对象实例
    private static volatile Singleton06 SINGLETON;

    // 提供一个静态的公有方法，当使用到该方法时，才去创建实例，加入同步处理，解决线程安全问题，同时解决懒加载问题
    public static synchronized Singleton06 getInstance() {
        if (SINGLETON == null) {
            synchronized (Singleton06.class) {
                if (SINGLETON == null) {
                    SINGLETON = new Singleton06();
                }
            }
        }
        return SINGLETON;
    }
}
