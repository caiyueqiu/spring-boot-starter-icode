package com.icode.designpattern.pattern._18_observer;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:37
 */
@Slf4j
public class AppClientObserve implements Observe {

    private Subject weatherSubject;

    //注册观察者, 关联主题
    public AppClientObserve(Subject weatherSubject) {
        this.weatherSubject = weatherSubject;
        this.weatherSubject.registerObserve(this);
    }


    @Override
    public String name() {
        return "App客户端";
    }

    @Override
    public void update(Weather weather) {
        log.info("APP客户端：{} 最新天气：温度 {} ℃, 相对湿度 {} %, 风力 {} 级",
                weather.getDate(), weather.getTemperature(), weather.getHumidity(), weather.getWindPower());
    }
}
