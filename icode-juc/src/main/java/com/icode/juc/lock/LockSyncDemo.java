package com.icode.juc.lock;

/**
 * synchronized原理
 * 同步代码块：monitor enter、monitor exit
 * 同步方法：ACC_SYNCHRONIZED
 * 静态同步方法：ACC_STATIC、ACC_SYNCHRONIZED
 *
 * @author caiyq
 * @date 2022/6/3 23:20
 */
public class LockSyncDemo {
    public static void main(String[] args) throws Exception {

    }

    private final Object object = new Object();

    public void m1() {
        synchronized (object) {
            System.out.println("synchronized code block");
            throw new RuntimeException("Exception...");
        }
    }

    public synchronized void m2() {
        System.out.println("synchronized m2");
    }

    public static synchronized void m3() {
        System.out.println("static synchronized m3");
    }
}
