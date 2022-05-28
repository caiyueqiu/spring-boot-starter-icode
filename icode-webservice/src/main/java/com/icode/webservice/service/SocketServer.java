package com.icode.webservice.service;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/2 20:42
 */
@Slf4j
public class SocketServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
        boolean flag = true;
        while (flag) {
            // 接收客户端的请求
            log.info("监听客户端的数据：");
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            len = inputStream.read(buffer);
            String getData = new String(buffer, 0, len);
            log.info("从客户端获取的数据：{}", getData);
            // TODO 业务处理
            String outPutData = getData.toUpperCase();
            // 向客户端写数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(outPutData.getBytes(StandardCharsets.UTF_8));
            // 释放资源
            outputStream.close();
            inputStream.close();
            socket.close();
        }
        serverSocket.close();
    }
}
