package com.icode.service.impl;

import com.icode.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/26 16:25
 */
@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name;
    }
}
