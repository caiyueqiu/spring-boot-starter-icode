package com.icode.juc.lock;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/4 8:48
 */
public class SaleTicketDemo {
    public static void main(String[] args) throws Exception {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "c").start();
    }
}

// 资源类，模拟三个售票员卖完50张票
class Ticket {
    private int number = 50;

    private final Object lockObject = new Object();

    public void sale() {
        synchronized (lockObject) {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：" + (number--) + "，还剩下：" + number);
            }
        }
    }
}
