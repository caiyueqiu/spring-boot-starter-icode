package com.icode.spring.jdk;

import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 10:18
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        // 用于保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        EmployeeService service = new EmployeeServiceImpl();
        TransactionHandler handler = new TransactionHandler(service);
        Object object = Proxy.newProxyInstance(handler.getClass().getClassLoader(), service.getClass().getInterfaces(), handler);
        EmployeeService proxy = (EmployeeService) object;
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
