package com.icode._07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 22:10
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        TaskDemo task = new TaskDemo(1, 1000);
        // 执行对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 加入执行任务
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
    }
}

class TaskDemo extends RecursiveTask<Long> {
    private int start;
    private int end;
    private long sum;

    public TaskDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        System.out.println("任务" + start + "------" + end + "累加开始");
        // 大于100个数相加切分，小于直接加
        if (end - start <= 100) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            // 切分为2块
            int middle = start + 100;
            // 递归调用，且分为2个子任务
            TaskDemo task1 = new TaskDemo(start, middle);
            TaskDemo task2 = new TaskDemo(middle + 1, end);
            // 异步执行
            task1.fork();
            task2.fork();
            // 同步阻塞获取执行结果
            sum = task1.join() + task2.join();
        }
        return sum;
    }
}
