package com.icode.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/20 16:18
 */
public class LinuxCondition implements Condition {
    /**
     *
     * @param context 判断条件能使用的上下文
     * @param metadata 注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取IoC使用的BeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取当前环境
        Environment environment = context.getEnvironment();
        // 获取Bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        if (environment.getProperty("os.name").contains("Linux")) {
            return true;
        }
        return false;
    }
}
