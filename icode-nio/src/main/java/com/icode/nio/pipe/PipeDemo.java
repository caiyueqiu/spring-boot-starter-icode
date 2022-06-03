package com.icode.nio.pipe;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/2 22:31
 */
public class PipeDemo {

    public static void main(String[] args) throws Exception {
        PipeDemo pipeDemo = new PipeDemo();
        pipeDemo.pipe();
    }

    private void pipe() throws Exception {
        // 获取管道
        Pipe pipe = Pipe.open();
        // 获取Sink管道，用来传输数据
        Pipe.SinkChannel sinkChannel = pipe.sink();
        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("Hello".getBytes());
        byteBuffer.flip();
        // 写数据到Sink管道
        sinkChannel.write(byteBuffer);
        // 创建接收数据的Source管道
        Pipe.SourceChannel sourceChannel = pipe.source();
        // 从Source管道中读数据
        ByteBuffer sourceBuffer = ByteBuffer.allocate(1024);
        int read = sourceChannel.read(sourceBuffer);
        System.out.println(new String(sourceBuffer.array(), 0, read));
        // 关闭
        sourceChannel.close();
        sinkChannel.close();
    }
}
