package com.icode.config;

import com.icode.entity.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/20 16:43
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry BeanDefinition注册类，RegisterBeanDefinition手动注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean blue = registry.containsBeanDefinition("com.icode.entity.Blue");
        boolean gray = registry.containsBeanDefinition("com.icode.entity.Gray");
        if (blue && gray) {
            // 手动注册
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainrow", rootBeanDefinition);
        }
    }
}
