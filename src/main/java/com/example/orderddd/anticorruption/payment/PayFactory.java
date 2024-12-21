package com.example.orderddd.anticorruption.payment;

import java.math.BigDecimal;

public interface PayFactory {

    public void getPaymentRequest(BigDecimal amount);

    static PayFactory getPayFactory(String payType) {
        return switch (payType) {
            case "AliPay" -> new AliPay();
            case "WeChatPay" -> new WeChatPay();
            default -> throw new IllegalArgumentException("Unknown payment type: " + payType);
        };
    }
}

