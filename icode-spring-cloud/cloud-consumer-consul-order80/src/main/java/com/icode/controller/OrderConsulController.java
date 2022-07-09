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
public class OrderConsulController {

    private static final String ORDER_CONSUL_URL = "http://CONSUL-PROVIDER-PAYMENT";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        return restTemplate.getForObject(ORDER_CONSUL_URL + "/payment/consul", String.class);
    }
}
