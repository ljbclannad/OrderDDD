package com.example.orderddd.domain.model.valueobject;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 值对象-金额
 * 
 * @author lejb
 * @version 1.0
 */
@Data
public class Money {
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
