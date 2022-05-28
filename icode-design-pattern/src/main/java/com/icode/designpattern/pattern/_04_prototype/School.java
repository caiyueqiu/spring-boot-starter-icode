package com.icode.designpattern.pattern._04_prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/26 22:07
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private String name;

//    /**
//     * 浅复制升级版使用
//     *
//     * @return
//     * @throws CloneNotSupportedException
//     */
//    @Override
//    protected School clone() throws CloneNotSupportedException {
//        return (School) super.clone();
//    }
}
