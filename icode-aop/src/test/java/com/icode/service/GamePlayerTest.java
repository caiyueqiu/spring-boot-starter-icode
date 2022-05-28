package com.icode.service;

import com.icode.service.IGamePlayer;
import com.icode.service.impl.GamePlayer;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 11:08
 */
public class GamePlayerTest {
    public static void main(String[] args) {
        IGamePlayer gamePlayer = new GamePlayer("青山大侠");
        gamePlayer.login("qingshan", "123456");
        gamePlayer.killBoss();
        gamePlayer.upgrade();
    }
}
