package com.icode.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/11 21:14
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) throws Exception {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User jay = new User("jay", 22);
        User jj = new User("jj", 28);
        atomicReference.set(jay);
        System.out.println(atomicReference.compareAndSet(jay, jj) + " " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(jay, jj) + " " + atomicReference.get().toString());
    }
}

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String userName;
    private int age;
}
