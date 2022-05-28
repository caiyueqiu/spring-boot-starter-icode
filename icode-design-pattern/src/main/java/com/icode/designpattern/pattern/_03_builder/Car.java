package com.icode.designpattern.pattern._03_builder;

import lombok.Data;
import lombok.ToString;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/25 22:13
 */
@Data
@ToString
public class Car {

    /*** 引擎 */
    private String engine;

    /*** 座位 */
    private String seats;

    /*** 车轮 */
    private String wheels;

    /*** 车架 */
    private String frame;
}
