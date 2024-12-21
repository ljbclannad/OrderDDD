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

    /**
     * 减法
     * 操作：值对象可以被“操作”，但这些操作不会改变原有对象，而是返回一个新的值对象。例如，可以定义方法来执行计算、转换等，但结果是一个新的实例。
     * @param amount 金额
     * @return 结果
     */
    public Money subtract(Money amount) {
        if (this.amount.compareTo(amount.getAmount()) < 0) {
            throw new IllegalArgumentException("金额不足");
        }
        return new Money(this.amount.subtract(amount.getAmount()), this.currency);
    }

    /**
     * 加法
     * 
     * @param amount 金额
     * @return 结果
     */
    public Money add(Money amount) {
        return new Money(this.amount.add(amount.getAmount()), this.currency);
    }
}
