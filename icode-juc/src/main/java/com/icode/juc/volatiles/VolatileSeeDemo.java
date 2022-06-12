package com.icode.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 9:53
 */
public class VolatileSeeDemo {
    private static volatile boolean flag = true;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 进来了");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + " flag被设置为false，程序停止");
        }, "t1").start();
        TimeUnit.SECONDS.sleep(2);
        flag = false;
        System.out.println(Thread.currentThread().getName() + " 修改完成flag：" + flag);
    }
}
