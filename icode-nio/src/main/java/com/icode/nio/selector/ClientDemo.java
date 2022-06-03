package com.icode.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 19:40
 */
public class ClientDemo {
    public static void main(String[] args) throws Exception {
        // 获取通道，绑定主机和端口号
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        // 设置非阻塞模式
        socketChannel.configureBlocking(false);
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 写入数据
        byteBuffer.put("Hello World".getBytes());
        // 切换读写模式
        byteBuffer.flip();
        // 写入通道
        socketChannel.write(byteBuffer);
        // 清空Buffer
        byteBuffer.clear();
    }
}
