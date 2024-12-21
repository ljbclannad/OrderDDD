package com.example.orderddd.interfaces.remote;

import com.example.orderddd.anticorruption.payment.AliPay;
import com.example.orderddd.anticorruption.payment.PayFactory;
import com.example.orderddd.anticorruption.payment.WeChatPay;

public interface PaymentClient {

    PayFactory pay(PayFactory payFactory);

    static PaymentClient getPaymentClient(PayFactory payFactory) {
        if (payFactory instanceof AliPay) {
            return new AliPaymentClient();
        } else if (payFactory instanceof WeChatPay) {
            return new WeChatPaymentClient();
        }
        throw new IllegalArgumentException("Unknown payment factory: " + payFactory.getClass().getSimpleName());
    }
}
