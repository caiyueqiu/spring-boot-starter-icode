package com.icode.nio.filelock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 23:21
 */
public class FileLockDemo {

    public static void main(String[] args) throws Exception {
        FileLockDemo fileLockDemo = new FileLockDemo();
        fileLockDemo.fileLock();
    }

    private void fileLock() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.wrap("Hello".getBytes());
        String filePath = "F:\\temp\\01.txt";
        Path path = Paths.get(filePath);
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        fileChannel.position(fileChannel.size() - 1);
        // 加锁（独占锁）
        FileLock fileLock = fileChannel.lock();
        // 共享锁，不能写操作，报错NonReadableChannelException
        // FileLock fileLock = fileChannel.lock(0L, Long.MAX_VALUE, true);
        System.out.println("是否为共享锁：" + fileLock.isShared());
        fileChannel.write(byteBuffer);
        fileChannel.close();
        // 读文件
        readFile(filePath);
    }

    private void readFile(String filePath) throws Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readLine = bufferedReader.readLine();
        System.out.println("读取的内容：");
        while (readLine != null) {
            System.out.println(readLine);
            readLine = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
    }
}
