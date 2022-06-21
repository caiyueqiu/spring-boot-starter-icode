package com.icode._05_vmstack;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/19 18:31
 */
public class VirtualMethodTest {
    public static void main(String[] args) throws Exception {
        Son.print("coder");
//        Father father = new Father();
//        father.show("hello");
    }
}

class Father {
    public static void print(String str) {
        System.out.println("father" + str);
    }

    private void show(String str) {
        System.out.println("father" + str);
    }
}

class Son extends Father {

}
