package com.icode._05_vmstack;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/18 9:31
 */
public class SlotTest {
    private void localVal() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    private void localVal2() {
        {
            int a = 0;
            System.out.println(a);
        }
        // 此时的b就会复用a的槽位
        int b = 0;
    }
}
