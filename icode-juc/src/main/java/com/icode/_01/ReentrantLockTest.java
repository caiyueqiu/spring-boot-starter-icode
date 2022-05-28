package com.icode._01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/8 8:45
 */
public class ReentrantLockTest {

    private List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        final ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new Thread(() -> reentrantLockTest.insert(Thread.currentThread())).start();
        new Thread(() -> reentrantLockTest.insert(Thread.currentThread())).start();
    }

    private void insert(Thread thread) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(thread.getName() + " 获取锁");
            for (int i = 0; i < 5; i++) {
                list.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName() + " 释放锁");
            lock.unlock();
        }
    }
}
