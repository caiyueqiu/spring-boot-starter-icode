package com.icode.design.template;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/31 16:59
 */
public class ManagerMoneyHandler extends AbstractBusinessHandler{
    @Override
    public void handle() {
        System.out.println("money manage");
    }

    @Override
    public boolean isVip() {
        return false;
    }
}
