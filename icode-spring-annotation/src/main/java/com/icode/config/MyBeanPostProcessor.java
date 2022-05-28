package com.icode.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/10 21:56
 */
@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
     * 注意：方法返回值不能为null
     * 如果返回null，那么在后续的初始化方法将报空指针异常，或者通过getBean()方法获取不到Bean实例对象
     * 因为后置处理器从Spring IoC容器中取出Bean实例对象没有再次放回IoC容器中
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessBeforeInitialization->beanName：{}", beanName);
        return bean;
    }

    /**
     * 实例化、依赖注入、初始化完毕时执行
     * 注意：方法返回值不能为null
     * 如果返回null，那么在后续的初始化方法将报空指针异常，或者通过getBean()方法获取不到Bean实例对象
     * 因为后置处理器从Spring IoC容器中取出Bean实例对象没有再次放回IoC容器中
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessAfterInitialization->beanName：{}", beanName);
        return bean;
    }
}
