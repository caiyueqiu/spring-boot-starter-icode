package com.icode.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/1 22:10
 */
public class FileChannelDemo {
    public static void main(String[] args) throws Exception {
        FileChannelDemo fileChannelDemo = new FileChannelDemo();
//        fileChannelDemo.read();
//        fileChannelDemo.write();
//        fileChannelDemo.position();
//        fileChannelDemo.transferFrom();
//        fileChannelDemo.transferTo();
    }

    private void transferTo() throws Exception {
        RandomAccessFile from = new RandomAccessFile("F:\\temp\\02.txt", "rw");
        FileChannel fromChannel = from.getChannel();
        RandomAccessFile to = new RandomAccessFile("F:\\temp\\03.txt", "rw");
        FileChannel toChannel = to.getChannel();
        long position = 0;
        long count = fromChannel.size();
        // 将数据从FileChannel传输到其他的channel中
        fromChannel.transferTo(position, count, toChannel);
        from.close();
        to.close();
    }

    private void transferFrom() throws Exception {
        RandomAccessFile from = new RandomAccessFile("F:\\temp\\01.txt", "rw");
        FileChannel fromChannel = from.getChannel();
        RandomAccessFile to = new RandomAccessFile("F:\\temp\\02.txt", "rw");
        FileChannel toChannel = to.getChannel();
        long position = 0;
        long count = fromChannel.size();
        // 将数据从源通道传输到FileChannel中
        toChannel.transferFrom(fromChannel, position, count);
        from.close();
        to.close();
    }

    private void position() throws Exception {
        // 创建FileChannel
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\temp\\01.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println("截取前size=" + fileChannel.size());
        // 截取，将指定长度后面的部分删除
        fileChannel.truncate(5);
        System.out.println("截取后size=" + fileChannel.size());
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 读取数据到Buffer中
        int read = fileChannel.read(byteBuffer);
        // mark <= position <= limit <= capacity
        System.out.println("position=" + byteBuffer.position() + " limit=" + byteBuffer.limit() + " capacity=" + byteBuffer.capacity());
        while (read != -1) {
            System.out.println("读取了：" + read);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
                System.out.println("position=" + byteBuffer.position() + " limit=" + byteBuffer.limit() + " capacity=" + byteBuffer.capacity());
            }
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        fileChannel.close();
        randomAccessFile.close();
        System.out.println("读取数据到Buffer结束");
    }

    private void write() throws Exception {
        // 创建FileChannel
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\temp\\01.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        String newData = "Hello Java " + System.currentTimeMillis();
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        byteBuffer.put(newData.getBytes());
        byteBuffer.flip();
        // 因为无法保证write方法一次能向FileChannel中写入多少字节，所以需要循环调用write方法，直到Buffer中已经没有数据
        while (byteBuffer.hasRemaining()) {
            fileChannel.write(byteBuffer);
        }
        fileChannel.close();
        randomAccessFile.close();
        System.out.println("写数据到Channel结束");
    }

    private void read() throws Exception {
        // 创建FileChannel
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\temp\\01.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 读取数据到Buffer中
        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            System.out.println("读取了：" + read);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        fileChannel.close();
        randomAccessFile.close();
        System.out.println("读取数据到Buffer结束");
    }
}
