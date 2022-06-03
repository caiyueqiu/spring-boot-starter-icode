package com.icode.nio.channel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 10:46
 */
public class DatagramChannelDemo {

    public static void main(String[] args) {

    }

    private void sendDatagram() throws Exception {
        // 打开9999端口接收UDP数据包
        DatagramChannel datagramChannel = DatagramChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        // 发送数据
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.wrap("hello world".getBytes(StandardCharsets.UTF_8));
            datagramChannel.send(byteBuffer, address);
            System.out.println("发送完成");
            Thread.sleep(1000);
        }
    }

    private void receiveDatagram() throws Exception {
        // 打开9999端口接收UDP数据包
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(9999));
        // 接收数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            byteBuffer.clear();
            SocketAddress address = datagramChannel.receive(byteBuffer);
            byteBuffer.flip();
            System.out.println("接收数据的地址：" + address.toString());
            System.out.println("接收到的数据：" + StandardCharsets.UTF_8.decode(byteBuffer));
        }
    }

    private void connect() throws Exception {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(9999));
        // 连接
        datagramChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        // write
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello world".getBytes(StandardCharsets.UTF_8));
        datagramChannel.write(byteBuffer);
        // read
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        while (true) {
            readBuffer.clear();
            datagramChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println("接收到的数据：" + StandardCharsets.UTF_8.decode(readBuffer));
        }
    }
}
