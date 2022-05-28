package com.icode.designpattern.pattern._03_builder;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/25 22:15
 */
public interface CarBuilder {

    void createEngine();

    void createSeats();

    void createWheels();

    void createFrame();

    Car createCar();
}
