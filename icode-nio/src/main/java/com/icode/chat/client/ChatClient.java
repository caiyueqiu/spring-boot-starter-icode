package com.icode.chat.client;

import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 9:37
 */
public class ChatClient {

    public void startClient(String name) throws Exception {
        // 连接服务器
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        // 接收服务端响应消息
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 创建线程
        new Thread(new ClientThread(selector)).start();
        // 发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (StringUtils.isNotBlank(line)) {
                socketChannel.write(StandardCharsets.UTF_8.encode(name + "----" + line));
            }
        }
    }
}
