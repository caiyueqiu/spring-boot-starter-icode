package com.icode._08;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/14 21:44
 */
public class CompletableFutureTest5 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("加10任务开始");
                num += 10;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return num;
        }).thenApply(integer -> num * num)
                .thenAccept(integer -> System.out.println("子线程全部处理完成，最后调用了accept，结果为：" + integer));
    }
}
