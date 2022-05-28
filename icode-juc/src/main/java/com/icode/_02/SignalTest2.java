package com.icode._02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 10:38
 */
public class SignalTest2 {
    // 通信对象：0打印A，1打印B，2打印C
    private int number = 0;

    private Lock lock = new ReentrantLock();

    // A
    private Condition conditionA = lock.newCondition();
    // B
    private Condition conditionB = lock.newCondition();
    // C
    private Condition conditionC = lock.newCondition();

    public void printA(int j) {
        try {
            lock.lock();
            while (number != 0) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出A，第" + j + "轮开始");
            for (int i = 0; i < 5; i++) {
                System.out.println("A");
            }
            // 打印B
            number = 1;
            // 唤醒B
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int j) {
        try {
            lock.lock();
            while (number != 1) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出B，第" + j + "轮开始");
            for (int i = 0; i < 10; i++) {
                System.out.println("B");
            }
            // 打印C
            number = 2;
            // 唤醒C
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int j) {
        try {
            lock.lock();
            while (number != 2) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出C，第" + j + "轮开始");
            for (int i = 0; i < 15; i++) {
                System.out.println("C");
            }
            // 打印A
            number = 0;
            // 唤醒A
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SignalTest2 test = new SignalTest2();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                test.printA(i);
            }
        }, "A线程").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                test.printB(i);
            }
        }, "B线程").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                test.printC(i);
            }
        }, "C线程").start();
    }
}
