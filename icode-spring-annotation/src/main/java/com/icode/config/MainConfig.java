package com.icode.config;

import com.icode.entity.Person;
import com.icode.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:49
 */
@Configuration
@ComponentScans(value = {
        @ComponentScan(value = "com.icode",
                excludeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
                },
                includeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
                        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
                }, useDefaultFilters = false
        )
})
public class MainConfig {
    @Bean(value = "person")
    public Person person() {
        return new Person("张三", 19);
    }
}
