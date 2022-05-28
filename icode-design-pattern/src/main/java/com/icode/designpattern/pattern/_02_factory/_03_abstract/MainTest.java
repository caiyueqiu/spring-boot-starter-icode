package com.icode.designpattern.pattern._02_factory._03_abstract;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 23:00
 */
public class MainTest {

    public static void main(String[] args) {
        AbstractFactory huaWeiFactory = new HuaWeiFactory();
        Computer huaWeiComputer = huaWeiFactory.mackComputer();
        huaWeiComputer.playGame();
        Phone huaWeiPhone = huaWeiFactory.makePhone();
        huaWeiPhone.call("张三");

        AbstractFactory appleFactory = new AppleFactory();
        Computer appleComputer = appleFactory.mackComputer();
        appleComputer.playGame();
        Phone applePhone = appleFactory.makePhone();
        applePhone.call("李四");
    }
}
