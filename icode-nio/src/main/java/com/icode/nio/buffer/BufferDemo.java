package com.icode.nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 12:15
 */
public class BufferDemo {

    public static void main(String[] args) throws Exception {
        BufferDemo bufferDemo = new BufferDemo();
        bufferDemo.map();
    }

    private void map() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\temp\\01.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        mappedBuffer.put(0, (byte) 97);
        mappedBuffer.put(1023, (byte) 122);
        randomAccessFile.close();
    }

    private void direct() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("F:\\temp\\01.txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\temp\\02.txt");
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            byteBuffer.clear();
            int read = inChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            outChannel.write(byteBuffer);
        }
    }

    private void readOnly() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        // 缓冲区中的数据0-9
        for (int i = 0; i < byteBuffer.capacity(); ++i) {
            byteBuffer.put((byte) i);
        }
        // 创建只读缓冲区
        ByteBuffer readonlyBuffer = byteBuffer.asReadOnlyBuffer();
        // 改变原缓冲区的内容
        for (int i = 0; i < byteBuffer.capacity(); ++i) {
            byte b = byteBuffer.get(i);
            b *= 10;
            byteBuffer.put(i, b);
        }
        readonlyBuffer.position(0);
        readonlyBuffer.limit(byteBuffer.capacity());
        // 只读缓冲区的内容也随之改变
        while (readonlyBuffer.remaining() > 0) {
            System.out.println(readonlyBuffer.get());
        }
    }

    private void slice() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        // 缓冲区中的数据0-9
        for (int i = 0; i < byteBuffer.capacity(); ++i) {
            byteBuffer.put((byte) i);
        }
        // 创建子缓冲区
        byteBuffer.position(3);
        byteBuffer.limit(7);
        ByteBuffer slice = byteBuffer.slice();
        // 改变子缓冲区的内容
        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        while (byteBuffer.remaining() > 0) {
            System.out.println(byteBuffer.get());
        }
    }

    private void intBuffer() throws Exception {
        // 分配新的int缓冲区，参数为缓冲区容量， 新缓冲区的当前位置将为零，其界限(限制位置)将为其容量，它将具有一个底层实现数组，其数组偏移量将为零
        IntBuffer intBuffer = IntBuffer.allocate(8);
        for (int i = 0; i < intBuffer.capacity(); ++i) {
            int j = 2 * (i + 1);
            // 将给定整数写入此缓冲区的当前位置，当前位置递增
            intBuffer.put(j);
        }
        // 重设此缓冲区，将限制设置为当前位置，然后将当前位置设置为0
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

    private void buffer() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\temp\\01.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }
}
