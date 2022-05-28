package com.icode.cloneable;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/27 23:03
 */
public class Student implements Cloneable {

    private int age;
    private String name;

    @Override
    protected Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    public Student() {
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
