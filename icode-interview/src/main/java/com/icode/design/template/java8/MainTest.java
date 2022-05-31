package com.icode.design.template.java8;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/31 17:12
 */
public class MainTest {

    public static void main(String[] args){
        BankBusinessHandler handler = new BankBusinessHandler();
        handler.saveVip(new BigDecimal("1000"));
    }
}
