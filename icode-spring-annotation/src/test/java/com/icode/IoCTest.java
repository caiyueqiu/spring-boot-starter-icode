package com.icode;

import com.icode.config.MainConfig5;
import com.icode.config.MainConfig6;
import com.icode.config.MainConfig7;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 22:17
 */
public class IoCTest {

    @Test
    public void test01() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig7.class);
        context.close();
    }

    @Test
    public void test02() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig5.class);
        Object bean = context.getBean("colorFactoryBean");
        System.out.println(bean.getClass()); // class com.icode.entity.Color
        Object bean2 = context.getBean("&colorFactoryBean");
        System.out.println(bean2.getClass()); // class com.icode.config.ColorFactoryBean
    }
}
