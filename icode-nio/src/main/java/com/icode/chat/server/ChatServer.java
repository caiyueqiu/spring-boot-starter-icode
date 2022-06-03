package com.icode.chat.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 9:37
 */
public class ChatServer {
    public static void main(String[] args) throws Exception {
        ChatServer chatServer = new ChatServer();
        chatServer.startServer();
    }

    private void startServer() throws Exception {
        // 创建Selector
        Selector selector = Selector.open();
        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(8888));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // Channel注册到Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");
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
                // 根据状态，处理不同的业务
                if (selectionKey.isAcceptable()) {
                    handleAccept(serverSocketChannel, selector);
                } else if (selectionKey.isReadable()) {
                    handleRead(selector, selectionKey);
                }
                iterator.remove();
            }
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
            castOtherClient(msg.toString(), selector, socketChannel);
        }
    }

    private void castOtherClient(String msg, Selector selector, SocketChannel socketChannel) throws Exception {
        // 获取所有已经接入的客户端
        Set<SelectionKey> selectionKeySet = selector.keys();
        // 循环向所有Channel广播消息
        for (SelectionKey selectionKey : selectionKeySet) {
            Channel targetChannel = selectionKey.channel();
            // 不需要给自己发送
            if (targetChannel instanceof SocketChannel && targetChannel != socketChannel) {
                ((SocketChannel) targetChannel).write(StandardCharsets.UTF_8.encode(msg));
            }
        }
    }

    private void handleAccept(ServerSocketChannel serverSocketChannel, Selector selector) throws Exception {
        // 接入状态，创建SocketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 把Channel注册到Selector，监听read状态
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 客户端回复消息
        socketChannel.write(StandardCharsets.UTF_8.encode("欢迎进入聊天室"));
    }
}
