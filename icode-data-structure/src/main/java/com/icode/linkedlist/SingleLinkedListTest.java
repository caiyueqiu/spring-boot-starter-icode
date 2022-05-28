package com.icode.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/24 20:30
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.list();
        // 按照节点编号添加
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.list();
        // 修改节点
//        singleLinkedList.update(new HeroNode(2, "小卢", "玉麒麟"));
//        singleLinkedList.list();
        // 删除节点
//        singleLinkedList.delete(1);
//        singleLinkedList.list();
        // 获取单链表的节点个数
//        int length = getLength(singleLinkedList.getHead());
//        System.out.println(length);
        // 获取单链表中的倒数第k个节点
//        HeroNode node = findLastIndexNode(singleLinkedList.getHead(), 2);
//        System.out.println(node);
        // 链表反转
//        reverse(singleLinkedList.getHead());
//        singleLinkedList.list();
        // 反转打印
        reversePrint(singleLinkedList.getHead());
    }

    /**
     * 反转打印
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        // 空链表，无需打印
        if (head.getNext() == null) {
            return;
        }
        Stack<HeroNode> nodeStack = new Stack<>();
        HeroNode current = head.getNext();
        // 将链表的所有节点压入栈
        while (current != null) {
            nodeStack.push(current);
            // current后移，遍历效果
            current = current.getNext();
        }
        // 将栈中的节点进行打印
        while (nodeStack.size() > 0) {
            System.out.println(nodeStack.pop());
        }
    }

    /**
     * 链表反转
     *
     * @param head
     */
    public static void reverse(HeroNode head) {
        // 当前链表为空，或者只有一个节点，则无需反转
        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }
        // 定义一个辅助节点，帮助遍历原来的链表
        HeroNode current = head.getNext();
        // 当前节点的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode();
        // 遍历原来的链表，每遍历一个节点，将其取出，并放在新的链表的最前端
        while (current != null) {
            // 暂时保存当前节点的下一个节点
            next = current.getNext();
            // 将current节点的下一个节点指向新链表的最前端
            current.setNext(reverseHead.getNext());
            // 新的链表最前端指向当前节点，链表串联起来
            reverseHead.setNext(current);
            // current节点后移
            current = next;
        }
        head.setNext(reverseHead.getNext());
    }

    /**
     * 获取单链表中的倒数第k个节点
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.getNext() == null) {
            return null;
        }
        // 链表长度
        int size = getLength(head);
        // 遍历链表size-index位置
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义一个辅助变量
        HeroNode current = head.getNext();
        for (int i = 0; i < size - index; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * 获取单链表的节点个数（如果是带head节点的链表，不统计head节点）
     *
     * @param head 链表head节点
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.getNext() == null) {
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量
        HeroNode current = head.getNext();
        while (current != null) {
            length++;
            current = current.getNext();
        }
        return length;
    }
}

@Slf4j
class SingleLinkedList {
    // 初始化头节点，头节点不变化
    private HeroNode head = new HeroNode();

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 因为head节点不能动，因此需要一个辅助节点
        HeroNode temp = head;
        // 链表最后
        while (temp.getNext() != null) {
            // 如果没有到最后，将temp节点后移
            temp = temp.getNext();
        }
        // 当退出while循环时，说明temp已经到了链表最后。将最后这个节点的next指向新的节点
        temp.setNext(heroNode);
    }

    /**
     * 添加节点，根据排名排序，如果存在相同排名，则添加失败
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        // 因为head节点不能动，因此需要一个辅助节点来找到添加节点的位置
        // temp节点是位于添加位置的前一个节点，否则添加不了
        HeroNode temp = head;
        // 添加的节点编号是否存在，默认false
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() > heroNode.getNo()) {
                // 位置找到，就在temp节点后面添加
                break;
            } else if (temp.getNext().getNo() == heroNode.getNo()) {
                // 添加的节点编号已存在
                flag = true;
                break;
            }
            // temp节点后移
            temp = temp.getNext();
        }
        if (flag) {
            log.warn("添加的节点编号已存在，heroNode：{}", heroNode.getNo());
        } else {
            // 添加到temp节点后面
            heroNode.setNext(temp.getNext());
            temp.setNext(heroNode);
        }
    }

    /**
     * 修改节点信息，根据编号修改
     *
     * @param heroNode
     */
    public void update(HeroNode heroNode) {
        if (head.getNext() == null) {
            log.warn("链表为空");
        }
        // 因为head节点不能动，因此需要一个辅助节点
        HeroNode temp = head.getNext();
        // 表示是否找到需要修改的节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                // 遍历结束
                break;
            }
            if (temp.getNo() == heroNode.getNo()) {
                // 找到需要修改的节点
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            // 找到需要修改的节点
            temp.setName(heroNode.getName());
            temp.setNickName(heroNode.getNickName());
        } else {
            log.warn("没有找到需要修改的节点");
        }
    }

    /**
     * 删除节点
     *
     * @param no
     */
    public void delete(Integer no) {
        HeroNode temp = head;
        // 是否找到待删除节点
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() == no) {
                // 找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            // 删除
            temp.setNext(temp.getNext().getNext());
        } else {
            log.warn("待删除节点不存在");
        }
    }

    /**
     * 遍历节点
     */
    public void list() {
        if (head == null) {
            log.warn("链表为空");
            return;
        }
        // 因为head节点不能动，因此需要一个辅助节点
        HeroNode temp = head.getNext();
        // 是否到链表最后
        while (temp != null) {
            // 输出节点信息
            log.info("节点信息：{}", temp);
            // 将temp节点后移
            temp = temp.getNext();
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class HeroNode {
    private int no;
    private String name;
    private String nickName;
    private HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
