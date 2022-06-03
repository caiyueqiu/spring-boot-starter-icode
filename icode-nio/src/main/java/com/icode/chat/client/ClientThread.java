package com.icode.chat.client;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 10:32
 */
public class ClientThread implements Runnable {

    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            // 循环监听连接
            for (; ; ) {
                // 获取Channel数量
                int select = selector.select();
                if (select == 0) {
                    continue;
                }
                // 获取可用Channel
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        handleRead(selector, selectionKey);
                    }
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRead(Selector selector, SelectionKey selectionKey) throws Exception {
        // 从SelectionKey中获取到已经就绪的Channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 循环读取客户端发送的信息
        int read = socketChannel.read(byteBuffer);
        StringBuilder msg = new StringBuilder();
        if (read > 0) {
            byteBuffer.flip();
            msg.append(StandardCharsets.UTF_8.decode(byteBuffer));
        }
        // Channel注册到Selector，监听read
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 把客户端发送的消息，广播到其他客户端
        if (msg.length() > 0) {
            System.out.println(msg);
        }
    }
}
