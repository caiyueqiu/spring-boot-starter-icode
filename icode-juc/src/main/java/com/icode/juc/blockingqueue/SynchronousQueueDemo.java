package com.icode.juc.blockingqueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue是一个不存储元素的BlockingQueue
 * 每个put操作必须要等待一个take操作
 *
 * @author caiyq
 * @date 2022/6/9 22:08
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws Exception {
        SynchronousQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put("3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " " + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " " + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " " + blockingQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
