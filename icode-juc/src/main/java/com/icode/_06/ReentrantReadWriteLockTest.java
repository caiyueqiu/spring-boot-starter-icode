package com.icode._06;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 20:10
 */
public class ReentrantReadWriteLockTest {
}

class MyCache {
    // map集合
    private volatile Map<String, Object> map = new HashMap<>();
    // 创建读写锁对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 放数据
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写操作" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写操作完成" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object value = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在读操作" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读操作完成" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }
}
