package com.icode.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 10:19
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws Exception {
        ServerSocketChannelDemo serverSocketChannelDemo = new ServerSocketChannelDemo();
        serverSocketChannelDemo.serverSocketChannel();
    }

    private void serverSocketChannel() throws Exception {
        int port = 8888;
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello".getBytes());
        // 打开一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 循环监听
        while (true) {
            System.out.println("等待连接...");
            // 非阻塞模式下，不会阻塞在这里
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 没有新的连接传入，会执行到这里
            if (socketChannel == null) {
                System.out.println("没有新的连接传入");
                Thread.sleep(2000);
            } else {
                System.out.println("连接传入，连接地址：" + socketChannel.socket().getRemoteSocketAddress());
                byteBuffer.rewind();
                socketChannel.write(byteBuffer);
                socketChannel.close();
            }
        }
    }
}
