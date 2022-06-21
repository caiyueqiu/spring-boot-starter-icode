package com.icode._07_heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 *
 * @author caiyq
 * @date 2022/6/19 19:57
 */
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 1024)];

    public static void main(String[] args) throws Exception {
        ArrayList<HeapInstanceTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
