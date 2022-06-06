package com.icode.juc.synchronizedup;

/**
 * 锁消除
 * 从JIT角度看相当于无视它，synchronized(o)不存在了，这个锁对象并没有被共用扩散到其它线程使用，极端的说就是根本没有加这个锁对象的底层机器码，消除了锁的使用
 *
 * @author caiyq
 * @date 2022/6/5 19:30
 */
public class LockClearDemo {

    private static Object objectLock = new Object();

    public static void main(String[] args) {
        LockClearDemo lockClearDemo = new LockClearDemo();
        for (int i = 1; i <= 10; i++) {
            new Thread(lockClearDemo::m1, String.valueOf(i)).start();
        }
    }

    public void m1() {
//        synchronized (objectLock) {
//            System.out.println("LockClearDemo");
//        }
        // 锁消除问题，JIT编译器会无视它，synchronized(o)，每次new出来的，不存在了，非正常的
        Object o = new Object();
        synchronized (o) {
            System.out.println("LockClearDemo " + o.hashCode() + " " + objectLock.hashCode());
        }
    }
}
