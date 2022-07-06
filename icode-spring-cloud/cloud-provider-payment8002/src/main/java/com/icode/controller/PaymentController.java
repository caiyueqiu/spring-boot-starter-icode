package com.icode.controller;

import com.icode.entity.CommonResult;
import com.icode.entity.Payment;
import com.icode.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/7/5 22:52
 */
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<>(200, "success，serverPort：" + serverPort, payment);
        } else {
            return new CommonResult<>(444, "fail");
        }
    }

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult<>(200, "success", result);
        } else {
            return new CommonResult<>(444, "fail");
        }
    }
}
