package com.example.orderddd.anticorruption.payment;

import java.math.BigDecimal;

public class WeChatPay implements PayFactory {

    @Override
    public void getPaymentRequest(BigDecimal amount) {
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentRequest'");
    }

}
