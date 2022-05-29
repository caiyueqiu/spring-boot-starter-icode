package com.icode.spring.loop;

import lombok.Data;

/**
 * 此处慎用@Data注解，具体可关注class
 *
 * @author caiyq
 * @date 2022/5/28 12:52
 */
@Data
public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
