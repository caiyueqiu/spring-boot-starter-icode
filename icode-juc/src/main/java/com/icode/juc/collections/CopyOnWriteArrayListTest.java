package com.icode.juc.collections;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/19 16:02
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) throws Exception {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
