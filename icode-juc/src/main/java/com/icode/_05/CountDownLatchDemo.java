package com.icode._05;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 15:28
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    if (Thread.currentThread().getName().equals("同学6")) {
                        Thread.sleep(2000);
                    }
                    System.out.println(Thread.currentThread().getName() + "放学了");
                    // 计数器减1，不会阻塞
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "同学" + i).start();
        }
        // 主线程await休息
        System.out.println("主线程睡觉");
        countDownLatch.await();
        // 其他线程全部完成后自动唤醒主线程
        System.out.println("全部放学了，现在的计数器为：" + countDownLatch.getCount());
    }
}
