package com.icode._04;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 14:06
 */
public class CallableTest {

    public static void main(String[] args) throws Exception {
        MyThread1 runnable = new MyThread1();
        MyThread2 callable = new MyThread2();
        FutureTask<Long> futureTask = new FutureTask<>(callable);
        new Thread(futureTask, "线程2").start();
        for (int i = 0; i < 10; i++) {
            Long result = futureTask.get();
            System.out.println(result);
        }
        new Thread(runnable, "线程1").start();
    }

    static class MyThread1 implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "线程进入了run方法");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThread2 implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            try {
                System.out.println(Thread.currentThread().getName() + "线程进入了call方法，准备睡觉");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "睡醒了");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return System.currentTimeMillis();
        }
    }
}
