package com.icode.cloneable;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/27 23:04
 */
public class School implements Cloneable {

    private String location;
    private Student student;

    @Override
    protected School clone() throws CloneNotSupportedException {
        // 浅拷贝
//        return (School) super.clone();
        // 深拷贝
        School school = (School) super.clone();
        school.setStudent(school.getStudent().clone());
        return school;
    }

    public School() {
    }

    public School(String location, Student student) {
        this.location = location;
        this.student = student;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
