package com.icode.designpattern.pattern._05_adapter;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/28 11:17
 */
public class CarFactoryImplAUDI implements CarFactory {

    public Car createCar() {
        return new Car("白色", "奥迪");
    }
}
