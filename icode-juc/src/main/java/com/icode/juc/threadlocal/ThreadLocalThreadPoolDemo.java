package com.icode.juc.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 必须回收自定义的ThreadLocal变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题
 * 尽量在代理中使用try-finally块进行回收
 *
 * @author caiyq
 * @date 2022/6/4 18:39
 */
public class ThreadLocalThreadPoolDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        MyData myData = new MyData();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer before = myData.threadLocalField.get();
                        myData.add();
                        Integer after = myData.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName() + " before：" + before + "，after：" + after);
                    } finally {
                        myData.threadLocalField.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

class MyData {
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocalField.set(1 + threadLocalField.get());
    }
}
