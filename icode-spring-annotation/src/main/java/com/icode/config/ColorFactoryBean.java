package com.icode.config;

import com.icode.entity.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/20 17:49
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        // 返回的对象会添加到容器中
        System.out.println("ColorFactoryBean...getObject...");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        // true：单实例，在容器中保存一份 false：多实例，每次获取都会创建实例
        return false;
    }
}
