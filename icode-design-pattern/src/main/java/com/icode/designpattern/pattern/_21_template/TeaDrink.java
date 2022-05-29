package com.icode.designpattern.pattern._21_template;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:09
 */
@Slf4j
public class TeaDrink extends Drink {
    @Override
    void addMaterials() {
        log.info("加入绿茶");
    }

    @Override
    void addOther() {
        log.info("加入蜂蜜");
    }
}
