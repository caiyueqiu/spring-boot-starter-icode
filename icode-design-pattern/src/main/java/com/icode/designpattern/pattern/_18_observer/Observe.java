package com.icode.designpattern.pattern._18_observer;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:36
 */
public interface Observe {

    String name();

    /**
     * @param weather 天气
     */
    void update(Weather weather);
}
