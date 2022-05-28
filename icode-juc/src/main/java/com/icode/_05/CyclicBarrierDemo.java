package com.icode._05;

import java.util.concurrent.CyclicBarrier;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 17:21
 */
public class CyclicBarrierDemo {
    private final static int number = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(number, () -> {
            System.out.println("集齐" + number + "颗龙珠，现在召唤！");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    if (Thread.currentThread().getName().equals("龙珠3号")) {
                        System.out.println("争夺龙珠3号开始");
                        Thread.sleep(5000);
                        System.out.println("抢到龙珠3号");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "收集到了");
                    }
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "龙珠" + i + "号").start();
        }
    }
}
