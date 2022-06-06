package com.icode.juc.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/4 10:22
 */
@Slf4j
public class InterruptDemo {

    private static volatile boolean isStop = false;
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {
        m6();
    }

    private static void m6() throws Exception {
        // 测试当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态，第二次再调用时中断状态已经被清除，将返回一个false
        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        System.out.println("1......");
        // 中断标志位设置为true
        Thread.currentThread().interrupt();
        System.out.println("2......");
        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        LockSupport.park();
        // 静态方法
        System.out.println("静态方法：" + Thread.interrupted());
        // 实例方法
        System.out.println("实例方法：" + Thread.currentThread().isInterrupted());
    }

    private static void m5() throws Exception {
        /*
            中断标志位，默认false
            t2  t1发出了中断协商，t2调用t1.interrupt()，中断标志位true
            中断标志位true，正常情况，程序停止
            中断标志位true，异常情况，InterruptedException，将会把中断状态将被清除，并且将收到InterruptedException，中断标志位false，导致无限循环
            在catch块中，需要再次给中断标志位设置为true，2次调用停止程序才OK
         */
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "中断标志位：true" + " 程序停止");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // 为什么要在异常处，再调用一次
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("m5");
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t1::interrupt, "t2").start();
    }

    private static void m4() throws Exception {
        // 实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println(i);
            }
            System.out.println("t1线程调用interrupt()后的的中断标识02：" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        System.out.println("t1线程默认的中断标识：" + t1.isInterrupted());// false
        TimeUnit.MILLISECONDS.sleep(2);
        t1.interrupt();
        System.out.println("t1线程调用interrupt()后的的中断标识01：" + t1.isInterrupted());// true
        TimeUnit.MILLISECONDS.sleep(2000);
        // 中断不活动的线程不会产生任何影响
        System.out.println("t1线程调用interrupt()后的的中断标识03：" + t1.isInterrupted());// false
    }

    private static void m3_isInterrupted() throws Exception {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "，isInterrupted()被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 interrupt");
            }
        }, "t1");
        t1.start();
        System.out.println("t1默认中断标志位：" + t1.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(20);
        // t2向t1发出协商，将t1的中断标志位设为true希望t1停下来
        new Thread(t1::interrupt, "t2").start();
        // t1自己停下
//        t1.interrupt();
    }

    private static void m2_atomicBoolean() throws Exception {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "，atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 atomicBoolean");
            }
        }, "t1").start();
        TimeUnit.MILLISECONDS.sleep(20);
        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1_volatile() throws Exception {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "，isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 volatile");
            }
        }, "t1").start();
        TimeUnit.MILLISECONDS.sleep(20);
        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
