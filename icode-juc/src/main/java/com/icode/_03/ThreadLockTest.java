package com.icode._03;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/13 13:38
 */
public class ThreadLockTest {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone.sendSms();
        phone.sendEmail();
        phone.hello();
    }
}

class Phone {
    public static synchronized void sendSms() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendSms");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("sendEmail");
    }

    public void hello() {
        System.out.println("hello");
    }
}
