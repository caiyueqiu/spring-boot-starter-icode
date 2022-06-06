package com.icode.juc.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 1、同一款产品，同时搜索出同款产品在各大电商平台的售价
 * 2、同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 *
 * @author caiyq
 * @date 2022/6/3 17:10
 */
public class CompletableFutureMallDemo {
    private static List<NetMall> mallList = Arrays.asList(
            new NetMall("京东"),
            new NetMall("当当"),
            new NetMall("淘宝"),
            new NetMall("拼多多"),
            new NetMall("天猫")
    );

    private static List<String> getPrice(List<NetMall> mallList, String productName) {
        return mallList.stream().map(netMall -> String.format(productName + " in %s price is %.2f",
                netMall.getNetMallName(), netMall.calcPrice(productName))).collect(Collectors.toList());
    }

    private static List<String> getPriceByCompletableFuture(List<NetMall> mallList, String productName) {
        return mallList.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(), netMall.calcPrice(productName))))
                .collect(Collectors.toList()).stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(getPrice(mallList, "MySQL"));
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        System.out.println(getPriceByCompletableFuture(mallList, "MySQL"));
        System.out.println("耗时：" + (System.currentTimeMillis() - start2));
    }
}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class NetMall {
    private String netMallName;

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}