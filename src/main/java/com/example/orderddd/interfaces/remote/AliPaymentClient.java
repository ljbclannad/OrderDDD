package com.example.orderddd.interfaces.remote;

import com.example.orderddd.anticorruption.payment.PayFactory;

public class AliPaymentClient implements PaymentClient {

    @Override
    public PayFactory pay(PayFactory payFactory) {
        throw new UnsupportedOperationException("Unimplemented method 'pay'");
    }

}
