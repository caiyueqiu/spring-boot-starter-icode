package com.icode.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2021/12/29 22:29
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/lock")
    public void testLock() {
        String uuId = UUID.randomUUID().toString();
        // 获取锁
        Boolean isGet = redisTemplate.opsForValue().setIfAbsent("lock", uuId, 3, TimeUnit.SECONDS);
        // 获取锁成功，查询数据
        if (isGet != null && isGet) {
            String value = redisTemplate.opsForValue().get("num");
            if (StringUtils.isBlank(value)) {
                return;
            }
            int num = Integer.parseInt(value + "");
            redisTemplate.opsForValue().set("num", String.valueOf(++num));
            // 释放锁
            if (uuId.equals(redisTemplate.opsForValue().get("lock"))) {
                redisTemplate.delete("lock");
            }
        } else {
            // 获取锁失败，每隔0.1秒再次获取
            try {
                Thread.sleep(100);
                testLock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/testLockLua")
    public void testLockLua() {
        String uuid = UUID.randomUUID().toString();
        // 定义一个锁，lua脚本可以使用同一把锁，来实现删除
        String key = "lock";
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, uuid, 3, TimeUnit.SECONDS);
        if (lock != null && lock) {
            String value = redisTemplate.opsForValue().get("num");
            if (StringUtils.isEmpty(value)) {
                return;
            }
            // 如果说在这出现了异常，那么delete就删除失败，也就是说锁永远存在
            int num = Integer.parseInt(value);
            redisTemplate.opsForValue().set("num", String.valueOf(++num));
            // 使用lua脚本来锁
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            // 使用redis执行lua
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            // 设置一下返回值类型为Long。因为删除判断的时候，返回的0，给其封装为数据类型。如果不封装那么默认返回String类型，那么返回字符串与0会有发生错误。
            redisScript.setResultType(Long.class);
            // 第一个要是script脚本，第二个需要判断的key，第三个就是key所对应的值
            redisTemplate.execute(redisScript, Arrays.asList(key), uuid);
        } else {
            // 其他线程等待
            try {
                // 睡眠
                Thread.sleep(1000);
                // 睡醒了之后，调用方法
                testLockLua();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
