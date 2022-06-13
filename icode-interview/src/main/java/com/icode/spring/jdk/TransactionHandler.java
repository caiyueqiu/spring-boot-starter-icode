package com.icode.spring.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 10:17
 */
public class TransactionHandler implements InvocationHandler {
    private EmployeeService service;
    private TransactionManager transactionManager = new TransactionManager();

    public TransactionHandler(EmployeeService service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            transactionManager.begin();
            method.invoke(service);
            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
        }
        return null;
    }
}
