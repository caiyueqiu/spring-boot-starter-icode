package com.icode._08;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 23:14
 */
public class CompletableFutureTest2 {
    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("子线程开始工作");
                Thread.sleep(5000);
                System.out.println("子线程工作结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 主线程阻塞
        future.get();
        System.out.println("主线程结束");
    }
}
