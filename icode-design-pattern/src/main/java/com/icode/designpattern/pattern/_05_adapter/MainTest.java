package com.icode.designpattern.pattern._05_adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/28 11:18
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) {
        CarFactory bmwCar = new CarFactoryImplBMW();
        CarAdapter carAdapter = new CarAdapter(bmwCar);
        Car car = carAdapter.createCar();
        log.info("before change color : {} ", car);
        carAdapter.changeColor(car, "红色");
        log.info("after change color : {} ", car);
    }
}
