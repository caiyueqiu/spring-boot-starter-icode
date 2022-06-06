package com.icode.juc.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 12:51
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "t1").start();
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("异步调用call方法");
        return "hello world";
    }
}
