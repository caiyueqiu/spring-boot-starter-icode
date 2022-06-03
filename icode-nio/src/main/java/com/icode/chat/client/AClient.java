package com.icode.chat.client;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 10:38
 */
public class AClient {
    public static void main(String[] args) throws Exception {
        ChatClient chatClient = new ChatClient();
        chatClient.startClient("张三");
    }
}
