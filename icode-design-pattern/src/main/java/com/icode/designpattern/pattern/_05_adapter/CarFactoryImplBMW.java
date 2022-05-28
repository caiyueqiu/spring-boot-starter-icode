package com.icode.designpattern.pattern._05_adapter;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/28 11:18
 */
public class CarFactoryImplBMW implements CarFactory {

    public Car createCar() {
        return new Car("白色", "宝马");
    }
}
