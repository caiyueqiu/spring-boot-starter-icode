package com.icode.cloneable;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/27 23:05
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) throws Exception {
        Student student = new Student(22, "周杰伦");
        School school = new School("江苏", student);
        log.info("school的地址：{}，school中的student的地址：{}", school.hashCode(), school.getStudent().hashCode());
        School cloneSchool = school.clone();
        log.info("cloneSchool的地址：{}，cloneSchool中的student的地址：{}", cloneSchool.hashCode(), cloneSchool.getStudent().hashCode());
    }
}
