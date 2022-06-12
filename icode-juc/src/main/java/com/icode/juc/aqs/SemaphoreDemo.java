package com.icode.juc.aqs;

import java.util.concurrent.Semaphore;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 9:24
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 1; i <= 6; i++) {
            Thread.sleep(100);
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "找车位中...");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "停车成功");
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "溜了溜了");
                    semaphore.release();
                }
            }, "汽车" + i).start();
        }
    }
}
