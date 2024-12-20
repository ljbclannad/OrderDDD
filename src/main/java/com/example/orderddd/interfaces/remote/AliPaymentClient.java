package com.example.orderddd.interfaces.remote;

import com.example.orderddd.anticorruption.payment.request.PaymentRequestFactory;
import com.example.orderddd.anticorruption.payment.response.PaymentResponseFactory;

public class AliPaymentClient implements PaymentClient {

    @Override
    public PaymentResponseFactory pay(PaymentRequestFactory paymentRequestFactory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pay'");
    }

}
