package com.icode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author caiyq
 * @date 2020/8/30 23:03
 */
@RestController
public class OrderZkController {

    private static final String ORDER_ZK_URL = "http://CLOUD-PROVIDER-PAYMENT";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(ORDER_ZK_URL + "/payment/zk", String.class);
    }
}
