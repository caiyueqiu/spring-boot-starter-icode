package com.icode.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 10:50
 */
@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public Dict test(String who) {
        log.info("/test方法调用开始...");
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }
}
