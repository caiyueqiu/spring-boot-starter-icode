package com.icode.design.template;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/31 16:38
 */
public class MainTest {

    public static void main(String[] args){
        AbstractBusinessHandler handler = new SaveMoneyHandler();
        handler.execute();
    }
}
