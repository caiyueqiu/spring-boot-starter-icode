package com.icode.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/11 21:18
 */
public class AtomicStampedDemo {
    public static void main(String[] args) throws Exception {
        Book javaBook = new Book(1, "javaBook");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);
        System.out.println("最初的值和版本号：" + stampedReference.getReference() + " " + stampedReference.getStamp());
        Book mysqlBook = new Book(2, "mysqlBook");
        boolean b;
        b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println("更新后的结果：" + b + " 更新后的值和版本号：" + stampedReference.getReference() + " " + stampedReference.getStamp());
        b = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println("更新后的结果：" + b + " 更新后的值和版本号：" + stampedReference.getReference() + " " + stampedReference.getStamp());
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
class Book {
    private int id;
    private String bookName;
}
