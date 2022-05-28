package com.icode._03;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 12:02
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, "线程" + i).start();
        }
    }
}
