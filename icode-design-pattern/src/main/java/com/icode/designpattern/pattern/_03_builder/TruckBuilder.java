package com.icode.designpattern.pattern._03_builder;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/25 22:16
 */
@Slf4j
public class TruckBuilder implements CarBuilder {
    Car car = new Car();

    @Override
    public void createEngine() {
        log.info("制造1000马力发动机");
        car.setEngine("1000马力发动机");
    }

    @Override
    public void createSeats() {
        log.info("制造2个座位");
        car.setSeats("2个座位");
    }

    @Override
    public void createWheels() {
        log.info("制造12个车轮");
        car.setWheels("12个车轮");
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
