package com.icode.designpattern.pattern._05_adapter;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/28 11:16
 */

public class CarAdapter implements Car4s {

    private CarFactory carFactory;

    public CarAdapter(CarFactory carFactory) {
        this.carFactory = carFactory;
    }

    public Car createCar() {
        return carFactory.createCar();
    }

    public Car changeColor(Car car, String color) {
        if (car == null) {
            return null;
        }
        car.setColor(color);
        return car;
    }
}
