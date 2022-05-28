package com.icode.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/20 10:54
 */
public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类的资源
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        if (className.contains("er")) {
            return true;
        }
        return false;
    }
}
