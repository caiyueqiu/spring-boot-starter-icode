package com.icode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/7/14 23:02
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;
}
