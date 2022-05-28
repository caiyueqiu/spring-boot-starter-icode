package com.icode.config;

import com.icode.entity.Blue;
import com.icode.entity.Gray;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:49
 */
@Configuration
@Import(value = {
        Blue.class,
        Gray.class,
        MyImportSelector.class,
        MyImportBeanDefinitionRegistrar.class
})
public class MainConfig4 {
}
