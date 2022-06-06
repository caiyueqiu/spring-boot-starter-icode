package com.icode.juc.completablefuture;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 12:51
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
        completableFutureDemo.test4();
    }

    private void test() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(3);
        // runAsync没有返回值
        // 不指定线程，默认是ForkJoinPool.commonPool-worker-1，指定线程就是当前线程
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, service);
        System.out.println(completableFuture.get());
        service.shutdown();
    }

    private void test2() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(3);
        // supplyAsync有返回值
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }, service);
        System.out.println(completableFuture.get());
        service.shutdown();
    }

    private void test3() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("输出结果：" + result);
            return result;
        }, service);
        System.out.println(completableFuture.get());
        service.shutdown();
    }

    private void test4() throws Exception {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("输出结果：" + result);
            return result;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("计算OK，输出结果：" + v);
            }
        }).exceptionally(e -> {
            System.out.println("出现异常：" + e.getCause() + "\t" + e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(5);
    }
}
