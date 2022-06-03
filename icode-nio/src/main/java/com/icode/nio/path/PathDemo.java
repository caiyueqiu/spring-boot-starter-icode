package com.icode.nio.path;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 8:07
 */
public class PathDemo {
    public static void main(String[] args) throws Exception {
        PathDemo pathDemo = new PathDemo();
        pathDemo.path();
    }

    private void path() throws Exception {
        Path path = Paths.get("F:\\temp\\01.txt");
        // 创建相对路径
        Path temp = Paths.get("F:", "temp");
        Path file = Paths.get("F:", "temp\\01.txt");
        // 使路径标准化，标准化意味着它将移除所有在路径字符串的中间的.和..代码，并解析路径字符串所引用的路径
        Path path2 = Paths.get("F:\\temp\\..\\01.txt");
        // path：F:\temp\..\01.txt
        System.out.println("path：" + path2);
        Path normalizePath = path2.normalize();
        // normalizePath：F:\01.txt
        System.out.println("normalizePath：" + normalizePath);
    }
}
