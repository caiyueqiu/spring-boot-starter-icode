package com.icode.juc.base;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/9 8:25
 */
public class SaleTicketDemo {
    public static void main(String[] args) throws Exception {
        SaleTicket saleTicket = new SaleTicket();
        new Thread(saleTicket, "窗口1").start();
        new Thread(saleTicket, "窗口2").start();
        new Thread(saleTicket, "窗口3").start();
    }
}

class SaleTicket implements Runnable {

    private int ticket = 100;
    private final Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 正在售出第" + ticket-- + "张票");
                } else {
                    break;
                }
            }
        }
    }
}
