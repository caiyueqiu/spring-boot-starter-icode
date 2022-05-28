package com.icode._08;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/14 21:49
 */
public class CompletableFutureTest8 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("加10任务开始");
            num += 10;
            return num;
        });
        //合并
        CompletableFuture<Integer> future1 = future.thenCompose(i ->
                //再来一个CompletableFuture
                CompletableFuture.supplyAsync(() -> i + 1));
        System.out.println(future.get());
        System.out.println(future1.get());
    }
}
