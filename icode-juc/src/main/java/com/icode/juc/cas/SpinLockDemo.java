package com.icode.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现自旋锁，CAS
 * 自旋锁的优点：循环比较获取没有类似wait的阻塞
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现当前有线程持有锁，所以只能通过自旋等待，直到A释放锁后B随后抢到
 *
 * @author caiyq
 * @date 2022/6/11 21:27
 */
public class SpinLockDemo {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) throws Exception {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        }, "A").start();
        // 暂停500毫秒，保证线程A先于B启动
        TimeUnit.MILLISECONDS.sleep(500);
        new Thread(() -> {
            spinLockDemo.lock();
            spinLockDemo.unLock();
        }, "B").start();
    }

    private void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " " + "进来了");
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }

    private void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " 任务结束，释放锁");
    }
}
