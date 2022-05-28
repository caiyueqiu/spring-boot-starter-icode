package com.icode._01;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/7 22:37
 */
public class Ticket {

    private int number;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "：卖出 " + number-- + " 剩下 " + number);
        }
    }
}
