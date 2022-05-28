package com.icode._02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 10:15
 */
public class SignalTest {

    public static void main(String[] args) {
//        Demo demo = new Demo();
        LockDemo demo = new LockDemo();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.increment();
            }
        }, "线程A").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.decrement();
            }
        }, "线程B").start();
    }
}

class Demo {
    private int number = 0;

    public synchronized void increment() {
        try {
            while (number != 0) {
                this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "加1成功，值为：" + number);
            notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void decrement() {
        try {
            while (number == 0) {
                this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "减1成功，值为：" + number);
            notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LockDemo {
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        try {
            lock.lock();
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "加1成功，值为：" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        try {
            lock.lock();
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "减1成功，值为：" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
