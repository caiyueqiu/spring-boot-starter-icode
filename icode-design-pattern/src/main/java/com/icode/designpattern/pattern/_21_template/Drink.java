package com.icode.designpattern.pattern._21_template;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/29 19:06
 */
@Slf4j
public abstract class Drink {

    /**
     * 抽象类定义公共方法，非公共部分需要子类实现
     * 定义流程的顺序，并防止子类修改顺序，子类只能修改其中一个步骤
     */
    final void mark() {
        fireWater();
        addMaterials();
        addOther();
        brewing();
    }

    public void fireWater() {
        log.info("开始烧水");
    }

    /**
     * 添加原料，如：咖啡、牛奶等
     */
    abstract void addMaterials();

    abstract void addOther();

    public void brewing() {
        log.info("开水冲泡");
    }
}
