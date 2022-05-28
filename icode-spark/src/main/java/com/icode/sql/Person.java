package com.icode.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * TODO
 *
 * @author caiyq
 * @date 2021/10/16 17:58
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    private String name;

    private Integer age;
}
