package com.icode.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 多线程并发调用一个类的初始化方法，如果未被初始化过，将执行初始化工作，要求只能被初始化一次，只有一个线程操作成功
 *
 * @author caiyq
 * @date 2022/6/11 22:36
 */
public class AtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) throws Exception {
        MyVar myVar = new MyVar();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    myVar.init(myVar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

class MyVar {
    public volatile Boolean isInit = Boolean.FALSE;
    private static final AtomicReferenceFieldUpdater<MyVar, Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");

    public void init(MyVar myVar) throws Exception {
        if (referenceFieldUpdater.compareAndSet(myVar, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + " 开始初始化，需要2秒");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " 结束初始化");
        } else {
            System.out.println(Thread.currentThread().getName() + " 已经有线程在进行初始化工作");
        }
    }
}
