package com.icode.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 18:51
 */
public class CompletableFutureApiDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

    }

    /**
     * 获得结果和触发计算
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void group1() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        //System.out.println(completableFuture.get());
        //System.out.println(completableFuture.get(2L,TimeUnit.SECONDS));
        //System.out.println(completableFuture.join());

        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        //System.out.println(completableFuture.getNow("xxx"));
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.get());
    }
}
