package com.icode._08;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/14 21:34
 */
public class CompletableFutureTest3 {
    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("子线程开始工作");
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "子线程工作结束";
        });
        // 主线程阻塞
        String result = future.get();
        System.out.println("主线程结束，子线程的返回结果为：" + result);
    }
}
