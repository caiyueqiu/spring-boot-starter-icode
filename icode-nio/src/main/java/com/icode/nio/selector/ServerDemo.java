package com.icode.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 19:31
 */
public class ServerDemo {
    public static void main(String[] args) throws Exception {
        // 获取服务端通道，绑定端口号
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 获取Selector
        Selector selector = Selector.open();
        // 通道注册到Selector，进行监听
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // Selector轮询，进行后续操作
        while (true) {
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 获取连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 注册
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();
                    }
                }
                iterator.remove();
            }
        }
    }
}
