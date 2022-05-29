package com.icode.designpattern.pattern._18_observer;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:38
 */
public class MainTest {

    public static void main(String[] args) {
        WeatherSubject weatherSubject = new WeatherSubject();
        Observe h5Observe = new H5ClientObserve(weatherSubject);
        Observe appObserve = new AppClientObserve(weatherSubject);

        weatherSubject.setWeather(new Weather("2020-4-16 12:00", 24, 50, 2));
        weatherSubject.setWeather(new Weather("2020-4-16 16:00", 20, 70, 3));

        weatherSubject.removeObserve(h5Observe);
        weatherSubject.setWeather(new Weather("2020-4-16 18:00", 15, 65, 1));
    }
}
