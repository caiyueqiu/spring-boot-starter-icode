package com.icode.kaiser;

/**
 * 凯撒加密
 *
 * @author caiyq
 * @date 2021/12/1 23:18
 */
public class KaiserDemo {
    public static void main(String[] args) {
        String input = "Hello world";
        // 往右边移动3位
        int key = 3;
        // 用来拼接
        StringBuilder sb = new StringBuilder();
        // 字符串转换成字节数组
        char[] chars = input.toCharArray();
        for (char c : chars) {
            int asciiCode = c;
            // 移动3位
            asciiCode = asciiCode + key;
            char newChar = (char) asciiCode;
            sb.append(newChar);
        }
        System.out.println(sb.toString());
    }
}
