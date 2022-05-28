package com.icode.designpattern.pattern._02_factory._03_abstract;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:53
 */
@Slf4j
public class AppleComputer implements Computer {
    @Override
    public void playGame() {
        log.info("使用苹果电脑玩LOL");
    }
}
