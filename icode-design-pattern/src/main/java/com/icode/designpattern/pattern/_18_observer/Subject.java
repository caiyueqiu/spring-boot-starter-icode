package com.icode.designpattern.pattern._18_observer;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:35
 */
public interface Subject {

    // 注册观察者
    void registerObserve(Observe observe);

    // 移除观察者
    void removeObserve(Observe observe);

    // 通知观察者
    void notifyAllObserve();
}
