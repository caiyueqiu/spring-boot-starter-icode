package com.icode.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/9 7:41
 */
public class DaemonDemo {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始运行 " +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {
            }
        }, "t1");
        // 标记为守护线程，setDaemon要在start()方法之前使用
        t1.setDaemon(true);
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " end 主线程");
    }
}
