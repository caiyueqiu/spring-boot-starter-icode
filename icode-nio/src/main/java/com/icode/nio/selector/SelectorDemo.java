package com.icode.nio.selector;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 18:56
 */
public class SelectorDemo {
    public static void main(String[] args) throws Exception {

    }

    private void selector() throws Exception {
        // 创建Selector
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 将Channel注册到Selector，并监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 查询已经就绪的通道的操作
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        selectionKeys.forEach(selectionKey -> {
            if (selectionKey.isAcceptable()) {
                System.out.println("a connection was accepted by a ServerSocketChannel");
            } else if (selectionKey.isConnectable()) {
                System.out.println("a connection was established with a remote server");
            } else if (selectionKey.isReadable()) {
                System.out.println("a channel is ready for reading");
            } else if (selectionKey.isWritable()) {
                System.out.println("a channel is ready for writing");
            }
        });
    }
}
