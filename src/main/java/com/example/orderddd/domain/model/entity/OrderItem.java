package com.example.orderddd.domain.model.entity;

import com.example.orderddd.domain.model.valueobject.Money;

import lombok.Data;

/**
 * 实体-订单项
 * 
 * @author lejb
 * @version 1.0
 */
@Data
public class OrderItem {
    /**
     * 订单项ID
     */
    private final String orderItemId;
    /**
     * 订单
     */
    private String orderId;
    /**
     * 商品ID
     */
    private final String productId;
    /**
     * 商品价格
     */
    private final Money price;
    /**
     * 商品数量
     */
    private final int quantity;
}
