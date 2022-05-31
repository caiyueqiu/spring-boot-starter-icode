package com.icode.design.template;

import org.apache.commons.lang3.RandomUtils;

/**
 * 模板方法模式
 * 1、模板方法模式是一种类的行为型模式，在它的结构图中只有类之间的继承关系，没有对象关联关系。
 * 2、模板方法模式是基于继承的代码复用基本技术，模板方法模式的结构和用法也是面向对象设计的核心之一。在模板方法模式中，可以将相同的代码放在父类中，而将不同的方法实现放在不同的子类中。
 * 3、在模板方法模式中，我们需要准备一个抽象类，将部分逻辑以具体方法以及具体构造函数的形式实现，然后声明一些抽象方法来让子类实现剩余的逻辑。不同的子类可以以不同的方式实现这些抽象方法，从而对剩余的逻辑有不同的实现，这就是模板方法模式的用意。模板方法模式体现了面向对象的诸多重要思想，是一种使用频率较高的模式。
 * 4、钩子是一种方法，它在抽象类中不做事，或者是默认的事情，子类可以选择覆盖它。
 * 5、为了防止子类改变模板方法中的算法骨架，一般将模板方法声明为final。
 * 6、策略模式和模板方法都是用于封装算法，前者是利用组合和委托模型，而后者则是继承。
 *
 * @author caiyq
 * @date 2022/5/31 16:33
 */
public abstract class AbstractBusinessHandler {

    public final void execute() {
        // 如果是VIP客户，可以不用排队
        if (!isVip()) {
            getRowNumber();
        }
        handle();
        judge();
    }

    /**
     * 取号
     */
    private void getRowNumber() {
        System.out.println("rowNumber-00" + RandomUtils.nextInt());
    }

    /**
     * 抽象的办理业务方法，由子类实现
     */
    public abstract void handle();

    /**
     * 评价
     */
    private void judge() {
        System.out.println("give a praised");
    }

    /**
     * 抽象的钩子方法，由子类实现
     *
     * @return
     */
    public abstract boolean isVip();
}
