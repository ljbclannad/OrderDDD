package com.example.orderddd.interfaces.remote;

import com.example.orderddd.anticorruption.payment.request.PaymentRequestFactory;
import com.example.orderddd.anticorruption.payment.response.PaymentResponseFactory;

public interface PaymentClient {

    PaymentResponseFactory pay(PaymentRequestFactory paymentRequestFactory);
}
