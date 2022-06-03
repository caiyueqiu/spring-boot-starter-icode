package com.icode.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 8:58
 */
public class CharsetDemo {
    public static void main(String[] args) throws Exception {
        CharsetDemo charsetDemo = new CharsetDemo();
        charsetDemo.charset();
    }

    private void charset() throws Exception {
        Charset charset = StandardCharsets.UTF_8;
        // 获取编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();
        // 获取解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();
        // 获取需要解码编码的数据
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("我爱你中国");
        buffer.flip();
        // 编码
        ByteBuffer byteBuffer = charsetEncoder.encode(buffer);
        System.out.println("编码后：");
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println(byteBuffer.get());
        }
        // 解码
        byteBuffer.flip();
        CharBuffer charBuffer = charsetDecoder.decode(byteBuffer);
        System.out.println("解码后：");
        System.out.println(charBuffer.toString());
        System.out.println("执行其他格式解码：");
        Charset gbk = Charset.forName("GBK");
        byteBuffer.flip();
        CharBuffer gbkCharBuffer = gbk.decode(byteBuffer);
        System.out.println(gbkCharBuffer.toString());
        // 获取Charset所支持的字符编码
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue().toString());
        }
    }
}
