package com.icode.designpattern.pattern._01_singleton;

/**
 * 单例模式
 * 枚举
 *
 * @author caiyq
 * @date 2022/2/20 22:30
 */
public enum Singleton08 {
    INSTANCE;

    public void say() {
        System.out.println("hello");
    }
}
