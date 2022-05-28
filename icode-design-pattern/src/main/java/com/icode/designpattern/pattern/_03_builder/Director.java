package com.icode.designpattern.pattern._03_builder;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/25 22:19
 */
public class Director {

    public Car buildCar(CarBuilder builder) {
        builder.createEngine();
        builder.createFrame();
        builder.createSeats();
        builder.createWheels();
        return builder.createCar();
    }
}
