package com.icode._08;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/14 21:53
 */
public class CompletableFutureTest11 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture[] futures = new CompletableFuture[4];
        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("加10任务开始");
                num += 10;
                return num;
            } catch (Exception e) {
                return 0;
            }
        });
        futures[0] = job1;
        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("乘以10任务开始");
                num = num * 10;
                return num;
            } catch (Exception e) {
                return 1;
            }
        });
        futures[1] = job2;
        CompletableFuture<Integer> job3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("减以10任务开始");
                num = num * 10;
                return num;
            } catch (Exception e) {
                return 2;
            }
        });
        futures[2] = job3;
        CompletableFuture<Integer> job4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
                System.out.println("除以10任务开始");
                num = num * 10;
                return num;
            } catch (Exception e) {
                return 3;
            }
        });
        futures[3] = job4;
        CompletableFuture<Object> future = CompletableFuture.anyOf(futures);
        System.out.println(future.get());
    }
}
