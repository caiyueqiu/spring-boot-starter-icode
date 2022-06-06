package com.icode.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock = ReentrantReadWriteLock + 读的过程中也允许获取写锁介入
 *
 * @author caiyq
 * @date 2022/6/6 22:00
 */
public class StampedLockDemo {

    private static int number = 37;
    private static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) throws Exception {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
//        new Thread(() -> {
//            try {
//                stampedLockDemo.read();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, "readThread").start();
//        TimeUnit.SECONDS.sleep(1);
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + " 写线程进入");
//            stampedLockDemo.write();
//        }, "writeThread").start();
//        TimeUnit.SECONDS.sleep(4);
//        System.out.println(Thread.currentThread().getName() + " number值为：" + number);

        new Thread(() -> {
            try {
                stampedLockDemo.tryOptimisticRead();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "readThread").start();
        // 读过程可以写介入
        TimeUnit.SECONDS.sleep(2);
        // 没有介入
//        TimeUnit.SECONDS.sleep(6);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 写线程进入");
            stampedLockDemo.write();
        }, "writeThread").start();
    }

    private void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + " 写线程准备修改");
        try {
            number = number + 13;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + " 写线程结束修改");
    }

    // 悲观读，读没有完成时候写锁无法获得锁
    public void read() throws Exception {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + " 读锁锁定，4秒中后继续");
        for (int i = 0; i < 4; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " 正在读取中...");
        }
        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + " 获得成员变量值result：" + result);
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    // 乐观读，读的过程中也允许获取写锁介入
    private void tryOptimisticRead() throws Exception {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        // 故意间隔4秒钟，很乐观认为读取中没有其它线程修改过number值，具体靠判断
        System.out.println("4秒前stampedLock.validate方法值(true无修改，false有修改) " + stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " 正在读取..." + i + "秒" +
                    "后stampedLock.validate方法值(true无修改，false有修改) " + stampedLock.validate(stamp));
        }
        if (!stampedLock.validate(stamp)) {
            System.out.println("有人修改过，有写操作，从乐观读升级为悲观读");
            stamp = stampedLock.readLock();
            try {
                result = number;
                System.out.println("重新悲观读后result：" + result);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " 最终的结果：" + result);
    }
}
