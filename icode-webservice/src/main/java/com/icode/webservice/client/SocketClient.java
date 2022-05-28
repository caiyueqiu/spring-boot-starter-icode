package com.icode.webservice.client;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/2 21:19
 */
@Slf4j
public class SocketClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        log.info("请输入数据：");
        String inputData = scanner.nextLine();
        // 开启一个Socket端口
        Socket socket = new Socket("127.0.0.1", 6666);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(inputData.getBytes());
        // 获取服务端回传的数据
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        len = inputStream.read(buffer);
        String getData = new String(buffer, 0, len);
        log.info("从服务端获取的数据：{}", getData);
        // 释放资源
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
