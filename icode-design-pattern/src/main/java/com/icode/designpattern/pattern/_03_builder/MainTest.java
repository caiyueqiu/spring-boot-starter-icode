package com.icode.designpattern.pattern._03_builder;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/25 22:20
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) {
        Director worker = new Director();
        Car truck = worker.buildCar(new TruckBuilder());
        log.info("卡车制造完成：{}", truck);
        log.info("------------我是分割线------------");
        Car bus = worker.buildCar(new BusBuilder());
        log.info("巴士制造完成：{}", bus);
    }
}
