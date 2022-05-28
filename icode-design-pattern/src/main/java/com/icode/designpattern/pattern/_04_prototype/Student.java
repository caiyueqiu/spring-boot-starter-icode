package com.icode.designpattern.pattern._04_prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/26 22:06
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Cloneable, Serializable {

    private String name;
    private Integer age;

    private School school;
    private List<String> friends;

    @Override
    protected Student clone() throws CloneNotSupportedException {
        // 浅克隆
        return (Student) super.clone();
        // 浅克隆升级
//        Student student = (Student) super.clone();
//        student.school = school.clone();
//        List list = new ArrayList<String>();
//        list.addAll(friends);
//        student.friends = list;
//        return student;
    }

    /**
     * 深克隆 基于序列化，与cloneable无关
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Student deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        Student student = (Student) oi.readObject();
        return student;
    }
}
