package com.icode.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/9 22:54
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo();
        blockingQueueDemo.addAndRemove();
    }

    private void addAndRemove() throws Exception {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        try {
            System.out.println(blockingQueue.add("a"));
            System.out.println(blockingQueue.add("b"));
            System.out.println(blockingQueue.add("c"));
            System.out.println(blockingQueue.add("d")); // 队列满
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(blockingQueue.remove());
            System.out.println(blockingQueue.remove());
            System.out.println(blockingQueue.remove());
            System.out.println(blockingQueue.remove()); // 队列空
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void offerAndPoll() throws Exception {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));
    }
}
