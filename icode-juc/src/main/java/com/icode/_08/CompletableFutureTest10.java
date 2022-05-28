package com.icode._08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/14 21:52
 */
public class CompletableFutureTest10 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        List<CompletableFuture> list = new ArrayList<>();
        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("加10任务开始");
            num += 10;
            return num;
        });
        list.add(job1);
        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("乘以10任务开始");
            num = num * 10;
            return num;
        });
        list.add(job2);
        CompletableFuture<Integer> job3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("减以10任务开始");
            num = num * 10;
            return num;
        });
        list.add(job3);
        CompletableFuture<Integer> job4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("除以10任务开始");
            num = num * 10;
            return num;
        });
        list.add(job4);
        //多任务合并
        List<Integer> collect = list.stream().map(CompletableFuture<Integer>::join).collect(Collectors.toList());
        System.out.println(collect);
    }
}
