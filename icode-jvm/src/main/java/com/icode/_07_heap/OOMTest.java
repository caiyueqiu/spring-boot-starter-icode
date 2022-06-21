package com.icode._07_heap;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/19 19:40
 */
public class OOMTest {
    public static void main(String[] args) throws Exception {
        ArrayList<Picture> list = new ArrayList<>();
        while (true) {
            Thread.sleep(20);
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
}

@AllArgsConstructor
class Picture {
    private Integer data;
}
