package com.example.orderddd.anticorruption.cqrs.commond;

import lombok.Data;

@Data
public class PayOrderCommand {

    private String orderId;

    private String payType;

}
