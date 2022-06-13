package com.icode.spring.jdk;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 10:21
 */
public class TransactionManager {

    public void begin() {
        System.out.println("开启事务");
    }

    public void commit() {
        System.out.println("提交事务");
    }

    public void rollback() {
        System.out.println("回滚事务");
    }
}
