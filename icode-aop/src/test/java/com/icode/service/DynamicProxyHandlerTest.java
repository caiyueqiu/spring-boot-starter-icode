package com.icode.service;

import com.icode.handler.DynamicProxyHandler;
import com.icode.service.impl.GamePlayer;

import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 18:52
 */
public class DynamicProxyHandlerTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IGamePlayer gamePlayer = new GamePlayer("青山大侠");

        DynamicProxyHandler handler = new DynamicProxyHandler(gamePlayer);

        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(
                gamePlayer.getClass().getClassLoader(), new Class[]{IGamePlayer.class}, handler);
        System.out.println(proxy.getClass());

        proxy.login("qingshan", "123456");
        proxy.killBoss();
        proxy.upgrade();
    }
}
