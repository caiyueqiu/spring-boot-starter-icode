package com.icode.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/5 22:08
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    myResource.write(finalI + "", finalI + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    myResource.read(finalI + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(1);
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    myResource.write(finalI + "", finalI + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "新写锁线程->" + i).start();
        }
    }
}

// 模拟缓存
class MyResource {
    private Map<String, String> map = new HashMap<>();
    // ReentrantLock等价于synchronized
    Lock lock = new ReentrantLock();
    // ReentrantReadWriteLock，一体两面，读写互斥，读读共享
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key, String value) throws Exception {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入");
            map.put(key, value);
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(Thread.currentThread().getName() + " 完成写入");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) throws Exception {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取");
            String result = map.get(key);
            // 暂停2000毫秒，演示读锁没有完成之前，写锁无法获得
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " 完成读取：" + result);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
