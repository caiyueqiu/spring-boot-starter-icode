package com.icode.designpattern.pattern._02_factory._02_method;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:47
 */
public class AppleFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
        return new ApplePhone();
    }
}
