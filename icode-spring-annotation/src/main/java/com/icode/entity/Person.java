package com.icode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:45
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private Integer age;
}
