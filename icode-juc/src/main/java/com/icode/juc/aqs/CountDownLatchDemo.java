package com.icode.juc.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 9:17
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
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 计数器减1，不会阻塞
                    countDownLatch.countDown();
                }
            }, "同学" + i).start();
        }
        countDownLatch.await();
        // 其他线程全部完成后自动唤醒主线程
        System.out.println("全部放学了，现在的计数器为：" + countDownLatch.getCount());
    }
}
