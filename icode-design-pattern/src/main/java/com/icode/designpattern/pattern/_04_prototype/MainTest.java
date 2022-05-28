package com.icode.designpattern.pattern._04_prototype;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/26 22:16
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) throws Exception {
        School school = new School("王者学校");
        Student student = new Student("曹操", 18, school, Arrays.asList("小乔", "大乔"));
        log.info("student：{}，student地址：{}，student中的school地址：{}",
                student, student.hashCode(), student.getSchool().hashCode());

        // 浅克隆
        Student cloneStudent = student.clone();
        log.info("cloneStudent：{}，cloneStudent地址：{}，cloneStudent中的school地址：{}",
                cloneStudent, cloneStudent.hashCode(), cloneStudent.getSchool().hashCode());


        // 深克隆
//        Student cloneObj = stu.deepClone();
//        cloneObj.setName("克隆曹操");
//        cloneObj.getSchool().setName("克隆王者学校");
//        cloneObj.getFriends().add("复制大乔");
//        log.info("stu：{}，stu的hashcode：{}，school的hashcode：{}", stu, stu.hashCode(), stu.getSchool().hashCode());
//        log.info("cloneObj：{}，cloneObj的hashcode：{}，school的hashcode：{}", cloneObj, cloneObj.hashCode(), cloneObj.getSchool().hashCode());
    }
}
