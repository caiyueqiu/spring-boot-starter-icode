package com.icode.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 9:58
 */
public class VolatileNotAtomicDemo {
    public static void main(String[] args) throws Exception {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(myNumber.number);
    }
}

class MyNumber {
    volatile int number;

    public void addPlusPlus() {
        number++;
    }
}
