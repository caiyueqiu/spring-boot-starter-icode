package com.icode.juc.memory;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/5 7:58
 */
public class ObjectHeadDemo {
    public static void main(String[] args) throws Exception {
        // new一个对象，占内存多少
        Object o = new Object();
        // 个hashcode记录在对象的什么地方
        System.out.println(o.hashCode());

        synchronized (o) {

        }

        // 手动收集垃圾，15次可以从新生代到养老区
        System.gc();

        Customer c1 = new Customer();
        Customer c2 = new Customer();
        Customer c3 = new Customer();
    }
}

class Customer {
    int id;
    String customerName;
}
