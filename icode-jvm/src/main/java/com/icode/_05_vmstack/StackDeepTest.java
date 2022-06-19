package com.icode._05_vmstack;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/18 9:00
 */
public class StackDeepTest {
    private static int count = 0;

    private static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) throws Exception {
        try {
            recursion();
        } catch (Exception e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }
}
