package com.icode.designpattern.pattern._21_template;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:10
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) {
        Drink coffee = new CoffeeDrink();
        log.info("泡咖啡");
        coffee.mark();
        log.info("-------------------------");
        log.info("开始泡茶");
        Drink tea = new TeaDrink();
        tea.mark();
    }
}
