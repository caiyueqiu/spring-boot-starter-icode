package com.icode.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/20 16:41
 */
public class MyImportSelector implements ImportSelector {
    /**
     *
     * @param importingClassMetadata 当前标注@Import注解的类的所有注解信息
     * @return 要导入到容器中的组件全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.icode.entity.Yellow"};
    }
}
