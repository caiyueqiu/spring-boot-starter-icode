package com.icode.design.template;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/31 16:58
 */
public class DrawMoneyHandler extends AbstractBusinessHandler{
    @Override
    public void handle() {
        System.out.println("draw 1000");
    }

    @Override
    public boolean isVip() {
        return false;
    }
}
