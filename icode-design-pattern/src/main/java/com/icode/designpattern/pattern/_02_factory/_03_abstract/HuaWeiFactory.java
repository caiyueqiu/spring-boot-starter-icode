package com.icode.designpattern.pattern._02_factory._03_abstract;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:57
 */
public class HuaWeiFactory implements AbstractFactory {
    @Override
    public Computer mackComputer() {
        return new HuaWeiComputer();
    }

    @Override
    public Phone makePhone() {
        return new HuaWeiPhone();
    }
}
