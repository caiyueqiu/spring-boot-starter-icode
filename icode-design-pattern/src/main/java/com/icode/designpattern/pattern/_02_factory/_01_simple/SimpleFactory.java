package com.icode.designpattern.pattern._02_factory._01_simple;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/22 22:32
 */
@Slf4j
public class SimpleFactory {

    public Phone createPhone(String brand) {
        Phone phone = null;

        switch (brand) {
            case "HUAWEI":
                phone = new HuaWeiPhone();
                break;
            case "APPLE":
                phone = new ApplePhone();
                break;
            default:
                log.warn("暂不支持该品牌手机");
                break;
        }
        return phone;
    }
}
