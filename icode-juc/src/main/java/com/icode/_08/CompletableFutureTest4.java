package com.icode._08;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/14 21:39
 */
public class CompletableFutureTest4 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("增加10任务开始");
                num += 10;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return num;
        }).thenApply(number -> num * num);
        // 主线程阻塞
        Integer result = future.get();
        System.out.println("主线程结束，子线程的返回结果为：" + result);
    }
}
