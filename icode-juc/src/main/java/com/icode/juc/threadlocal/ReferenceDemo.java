package com.icode.juc.threadlocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/4 19:49
 */
public class ReferenceDemo {

    public static void main(String[] args) throws Exception {
        phantomReference();
    }

    private static void phantomReference() throws Exception {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        System.out.println(phantomReference.get());
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            while (true) {
                list.add(new byte[1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get() + "添加成功");
            }
        }, "t1").start();
        new Thread(() -> {
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("有虚对象回收加入了队列");
                    break;
                }
            }
        }, "t2").start();
    }

    private static void weakReference() throws Exception {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("GC之前内存够用：" + weakReference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GC之后内存够用：" + weakReference.get());
    }

    private static void softReference() throws Exception {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("softReference " + softReference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GC之后内存够用：" + softReference.get());
        // 添加虚拟机参数：-Xms10m -Xmx10m
        try {
            // 20MB对象
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("GC之后内内存不够：" + softReference.get());
        }
    }

    private static void strongReference() throws Exception {
        MyObject myObject = new MyObject();
        System.out.println("GC之前：" + myObject);
        myObject = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("GC之后：" + myObject);
    }
}

class MyObject {
    // 演示方法，实际中一般不用复写
    @Override
    protected void finalize() throws Throwable {
        // finalize：目的是在对象被不可撤销地丢弃之前执行清理操作
        System.out.println("调用finalize方法");
    }
}

