package com.icode.service;

import com.icode.entity.Payment;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/7/5 22:53
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
