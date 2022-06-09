package com.icode.juc.base;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/9 8:36
 */
public class ProducerConsumerSyncDemo {
    public static void main(String[] args) throws Exception {
        SyncCondition syncCondition = new SyncCondition();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                syncCondition.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                syncCondition.decrement();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                syncCondition.increment();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                syncCondition.decrement();
            }
        }, "D").start();
    }
}

class SyncCondition {
    private int number = 0;

    public synchronized void increment() {
        while (number != 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " " + number);
        this.notifyAll();
    }

    public synchronized void decrement() {
        while (number == 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " " + number);
        this.notifyAll();
    }
}
