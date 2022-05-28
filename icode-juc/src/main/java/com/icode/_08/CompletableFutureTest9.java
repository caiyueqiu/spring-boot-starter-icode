package com.icode._08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/14 21:50
 */
public class CompletableFutureTest9 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("加10任务开始");
            num += 10;
            return num;
        });
        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("乘以10任务开始");
            num = num * 10;
            return num;
        });
        //合并两个结果
        CompletableFuture<Object> future = job1.thenCombine(job2, (BiFunction<Integer, Integer, List<Integer>>) (a, b) -> {
            List<Integer> list = new ArrayList<>();
            list.add(a);
            list.add(b);
            return list;
        });
        System.out.println("合并结果为：" + future.get());
    }
}
