package com.icode.designpattern.pattern._18_observer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:35
 */
@Slf4j
public class WeatherSubject implements Subject {

    private List<Observe> observes = new ArrayList<>();

    private Weather weather;

    /**
     * 实现特定顺序通知观察者，可以自我实现排序方式
     * 如果使用队列保存观者，需提前排序
     *
     * @param observe
     */
    @Override
    public void registerObserve(Observe observe) {
        observes.add(observe);
    }

    @Override
    public void removeObserve(Observe observe) {
        log.info("移除观察者 {}", observe.name());
        observes.remove(observe);
    }


    /**
     * 方式一、直接将更新内容发送给观察者
     * 推模式：通知你发生变化的同时，通过一个参数将变化的细节传递到观察者角色中去。
     * <p>
     * 方式二、通知消息给观察者拉去更新数据
     * 拉模式：目标角色在发生变化后，仅仅告诉观察者角色“我变化了”；观察者角色如果想要知道具体的变化细节，则就要自己从目标角色的接口中得到。
     */
    @Override
    public void notifyAllObserve() {
        if (observes.isEmpty()) {
            log.info("暂无观察者");
            return;
        }
        observes.forEach((observe) -> {
            observe.update(weather);
        });
    }

    /**
     * 天气更新，通知观察者。
     * 与监听者模式差别在于 事件处理（事件源 + 事件 + 事件监听）
     *
     * @param weather
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
        notifyAllObserve();

        // 监听模式，事件处理。可以使用外观模式封装事件处理
        //eventHandle.notifyAllObserve(observes)
    }
}
