package com.icode.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/4 10:19
 */
public class LockSupportDemo {

    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws Exception {
        lockSupport();
    }

    private static void lockSupport() throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 进来了 " + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 被唤醒 " + System.currentTimeMillis());
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + " 发出通知");
        }, "t2").start();
    }

    private static void lockAwaitSignal() throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 进来了");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " 发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void syncWaitNotify() throws Exception {
        Object objectLock = new Object();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + " 进来了");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + " 发出通知");
            }
        }, "t2").start();
    }
}
