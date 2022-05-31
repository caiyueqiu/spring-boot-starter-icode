package com.icode.design.template;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/31 16:38
 */
public class SaveMoneyHandler extends AbstractBusinessHandler{
    @Override
    public void handle() {
        System.out.println("save 1000");
    }

    @Override
    public boolean isVip() {
        return false;
    }
}
