package com.icode.designpattern.pattern._02_factory._02_method;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:47
 */
public class MainTest {

    public static void main(String[] args) {
        HuaWeiFactory huaWeiFactory = new HuaWeiFactory();
        Phone huaWeiPhone = huaWeiFactory.createPhone();
        huaWeiPhone.call("张三");

        AppleFactory appleFactory = new AppleFactory();
        Phone applePhone = appleFactory.createPhone();
        applePhone.call("李四");
    }
}
