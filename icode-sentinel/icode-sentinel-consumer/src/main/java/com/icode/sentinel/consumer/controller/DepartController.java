package com.icode.sentinel.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.icode.sentinel.consumer.bean.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 12:20
 */
@RestController
public class DepartController {
    @Autowired
    private RestTemplate restTemplate;

    @SentinelResource(value = "getDepartById", fallback = "getHandlerFallback")
    @GetMapping("/consumer/depart/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        String url = "http://abcmsc-provider-depart/provider/depart/get/" + id;
        return restTemplate.getForObject(url, Depart.class);
    }

    public Depart getHandlerFallback(int id) {
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("degrade-method-" + id);
        return depart;
    }
}

