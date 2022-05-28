package com.icode.designpattern.pattern._02_factory._03_abstract;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:58
 */
public class AppleFactory implements AbstractFactory {
    @Override
    public Computer mackComputer() {
        return new AppleComputer();
    }

    @Override
    public Phone makePhone() {
        return new ApplePhone();
    }
}
