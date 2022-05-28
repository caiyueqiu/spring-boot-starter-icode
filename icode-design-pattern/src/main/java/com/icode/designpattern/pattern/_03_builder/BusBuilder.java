package com.icode.designpattern.pattern._03_builder;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/25 22:17
 */
@Slf4j
public class BusBuilder implements CarBuilder{
    Car car = new Car();

    @Override
    public void createEngine() {
        log.info("制造200马力发动机");
        car.setEngine("200马力发动机");
    }

    @Override
    public void createSeats() {
        log.info("制造19个座位");
        car.setSeats("19个座位");
    }

    @Override
    public void createWheels() {
        log.info("制造4个车轮");
        car.setWheels("4个车轮");
    }

    @Override
    public void createFrame() {
        log.info("制造高强度车架");
        car.setFrame("高强度车架");
    }

    @Override
    public Car createCar() {
        return car;
    }
}
