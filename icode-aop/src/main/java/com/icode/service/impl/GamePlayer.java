package com.icode.service.impl;

import com.icode.service.IGamePlayer;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 15:27
 */
public class GamePlayer implements IGamePlayer {
    private String user = "";

    public GamePlayer(String user) {
        this.user = user;
    }

    @Override
    public void login(String user, String password) {
        System.out.println(user + "登陆成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.user + "在打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(this.user + "升级了");
    }
}
