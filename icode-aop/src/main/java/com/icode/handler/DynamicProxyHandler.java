package com.icode.handler;

import com.icode.service.IGamePlayer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 17:12
 */
public class DynamicProxyHandler implements InvocationHandler {
    private IGamePlayer gamePlayer;

    public DynamicProxyHandler(IGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("killBoss")) {
            System.out.println("带上麻痹戒指");
        }
        return method.invoke(gamePlayer, args);
    }
}
