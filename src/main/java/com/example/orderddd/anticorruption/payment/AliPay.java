package com.example.orderddd.anticorruption.payment;

import java.math.BigDecimal;

public class AliPay implements PayFactory {

    @Override
    public void getPaymentRequest(BigDecimal amount) {
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentRequest'");
    }

}
