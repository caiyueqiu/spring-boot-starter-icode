package com.icode._08;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 22:48
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "子线程开始工作");
                Thread.sleep(5000);
                // 在子线程中完成主线程
                future.complete("success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        // 主线程调用get方法阻塞
        System.out.println("主线程调用get方法获取结果为：" + future.get());
        System.out.println("主线程完成，阻塞结束");
    }
}
