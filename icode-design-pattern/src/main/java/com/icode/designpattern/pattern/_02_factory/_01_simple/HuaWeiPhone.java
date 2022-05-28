package com.icode.designpattern.pattern._02_factory._01_simple;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体产品类
 *
 * @author caiyq
 * @date 2022/5/22 22:29
 */
@Slf4j
public class HuaWeiPhone implements Phone {
    @Override
    public void make() {
        log.info("华为手机制作完成");
    }

    @Override
    public void call() {
        log.info("华为手机拨打电话");
    }
}
