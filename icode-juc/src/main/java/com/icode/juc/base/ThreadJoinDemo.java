package com.icode.juc.base;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/9 7:45
 */
public class ThreadJoinDemo {
    public static void main(String[] args) throws Exception {
        ThreadJoin t1 = new ThreadJoin();
        ThreadJoin t2 = new ThreadJoin();
        ThreadJoin t3 = new ThreadJoin();

        t1.setName("苏州");
        t2.setName("南京");
        t3.setName("无锡");

        t1.start();
        // 只有t1线程执行结束，其他线程才能开始执行
        t1.join();
        t2.start();
        t3.start();
    }
}

class ThreadJoin extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
