package com.icode.service;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 15:26
 */
public interface IGamePlayer {
    void login(String user, String password);

    void killBoss();

    void upgrade();
}
