package com.icode.nio.asynchronous;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 8:41
 */
public class AsynchronousFileChannelDemo {
    public static void main(String[] args) throws Exception {
        AsynchronousFileChannelDemo asynchronousFileChannelDemo = new AsynchronousFileChannelDemo();
        asynchronousFileChannelDemo.completionHandleWrite();
    }

    private void completionHandleWrite() throws Exception {
        Path path = Paths.get("F:\\temp\\01.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        // 参数1：指向与AsynchronousFileChannel相关联文件的Path实例
        // 参数2：一个或多个打开选项，告诉AsynchronousFileChannel在文件上执行什么操作
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long position = 0;
        byteBuffer.put("python".getBytes());
        byteBuffer.flip();
        asynchronousFileChannel.write(byteBuffer, position, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result：" + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("写数据失败");
            }
        });
    }

    private void future() throws Exception {
        Path path = Paths.get("F:\\temp\\01.txt");
        // 参数1：指向与AsynchronousFileChannel相关联文件的Path实例
        // 参数2：一个或多个打开选项，告诉AsynchronousFileChannel在文件上执行什么操作
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long position = 0;
        byteBuffer.put("java".getBytes());
        byteBuffer.flip();
        // ByteBuffer中的数据写入到文件中
        Future<Integer> future = asynchronousFileChannel.write(byteBuffer, position);
        byteBuffer.clear();
        while (!future.isDone()) ;
    }

    private void completionHandleRead() throws Exception {
        Path path = Paths.get("F:\\temp\\01.txt");
        // 参数1：指向与AsynchronousFileChannel相关联文件的Path实例
        // 参数2：一个或多个打开选项，告诉AsynchronousFileChannel在文件上执行什么操作
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long position = 0;
        asynchronousFileChannel.read(byteBuffer, position, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result：" + result);
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    private void asynchronous() throws Exception {
        Path path = Paths.get("F:\\temp\\01.txt");
        // 参数1：指向与AsynchronousFileChannel相关联文件的Path实例
        // 参数2：一个或多个打开选项，告诉AsynchronousFileChannel在文件上执行什么操作
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long position = 0;
        // 创建一个ByteBuffer，它被传递给read()方法作为参数，以及一个0的位置
        Future<Integer> future = asynchronousFileChannel.read(byteBuffer, position);
        // 在调用read()之后，循环，直到返回的isDone()方法返回true
        while (!future.isDone()) ;
        // 读取操作完成后，数据读取到ByteBuffer中
        byteBuffer.flip();
        byte[] data = new byte[byteBuffer.limit()];
        byteBuffer.get(data);
        System.out.println(new String(data));
        byteBuffer.clear();
    }
}
