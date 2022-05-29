package com.icode.designpattern.pattern._18_observer;

import lombok.Data;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:35
 */
@Data
public class Weather {

    private String date;
    private int temperature;
    private int humidity;
    private int windPower;

    public Weather(String date, int temperature, int humidity, int windPower) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windPower = windPower;
    }
}
