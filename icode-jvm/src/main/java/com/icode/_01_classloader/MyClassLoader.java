package com.icode._01_classloader;

import java.io.FileNotFoundException;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 12:09
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException();
    }

    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类，如果指定路径的字节码文件进行了加密，则需要在此方法中进行解密
        return null;
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = Class.forName("com", true, myClassLoader);
        Object object = clazz.newInstance();
        System.out.println(object.getClass().getClassLoader());
    }
}
