package com.icode.spring.loop;

import lombok.Data;

/**
 * 此处慎用@Data注解，具体可关注class
 *
 * @author caiyq
 * @date 2022/5/28 12:52
 */
public class A {

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
