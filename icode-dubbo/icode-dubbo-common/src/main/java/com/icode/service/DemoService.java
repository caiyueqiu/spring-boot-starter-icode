package com.icode.service;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/26 16:21
 */
public interface DemoService {

    String sayHello(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
