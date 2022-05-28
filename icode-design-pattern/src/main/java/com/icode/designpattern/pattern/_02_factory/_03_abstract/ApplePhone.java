package com.icode.designpattern.pattern._02_factory._03_abstract;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:56
 */
@Slf4j
public class ApplePhone implements Phone {
    @Override
    public void call(String receiver) {
        log.info("苹果手机拨打{}电话", receiver);
    }
}
