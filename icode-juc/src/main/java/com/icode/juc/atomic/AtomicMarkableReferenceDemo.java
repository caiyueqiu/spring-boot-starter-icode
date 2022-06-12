package com.icode.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/11 22:04
 */
public class AtomicMarkableReferenceDemo {
    private static AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " 默认标识：" + marked);
            // 等待后面的T2线程和我拿到一样的模式flag标识，都是false
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            markableReference.compareAndSet(100, 1000, marked, !marked);
        }, "t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " 默认标识：" + marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName() + " t2线程CAS结果：" + b);
            System.out.println(Thread.currentThread().getName() + " " + markableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + " " + markableReference.getReference());
        }, "t2").start();
    }
}
