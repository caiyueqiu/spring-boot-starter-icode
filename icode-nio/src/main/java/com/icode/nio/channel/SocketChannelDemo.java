package com.icode.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 10:32
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws Exception {
        SocketChannelDemo socketChannelDemo = new SocketChannelDemo();
        socketChannelDemo.socketChannel();
    }

    private void socketChannel() throws Exception {
        // 方式1
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        // 方式2
//        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
        // 连接校验
        System.out.println("SocketChannel是否为open状态：" + socketChannel.isOpen());
        System.out.println("SocketChannel是否已被连接：" + socketChannel.isConnected());
        System.out.println("SocketChannel是否正在进行连接：" + socketChannel.isConnectionPending());
        System.out.println("校验正在进行Socket连接SocketChannel是否已经完成连接：" + socketChannel.finishConnect());
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 读操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        // 如果是阻塞模式，将阻塞在读操作，无法打印这里
        System.out.println("读操作结束");
    }
}
