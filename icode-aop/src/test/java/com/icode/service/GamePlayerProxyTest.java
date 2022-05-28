package com.icode.service;

import com.icode.service.IGamePlayer;
import com.icode.service.impl.GamePlayer;
import com.icode.service.impl.GamePlayerProxy;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 11:08
 */
public class GamePlayerProxyTest {
    public static void main(String[] args) {
        IGamePlayer gamePlayer = new GamePlayer("青山大侠");
        IGamePlayer proxy = new GamePlayerProxy(gamePlayer);
        proxy.login("qingshan", "123456");
        proxy.killBoss();
        proxy.upgrade();
    }
}
