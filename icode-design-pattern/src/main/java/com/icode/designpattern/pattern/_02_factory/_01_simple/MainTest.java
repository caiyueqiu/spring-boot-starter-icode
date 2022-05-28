package com.icode.designpattern.pattern._02_factory._01_simple;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:34
 */
public class MainTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Phone huawei = simpleFactory.createPhone("HUAWEI");
        huawei.make();
        huawei.call();

        Phone apple = simpleFactory.createPhone("APPLE");
        apple.make();
        apple.call();
    }
}
