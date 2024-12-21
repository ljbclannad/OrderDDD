package com.example.orderddd.domain.model.aggregate;

import java.util.List;

import com.example.orderddd.domain.model.entity.OrderItem;
import com.example.orderddd.domain.model.valueobject.Money;
import com.example.orderddd.domain.model.valueobject.OrderStatus;

import lombok.Data;

@Data
public class Order {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 用户
     */
    private String userId;
    /**
     * 总金额
     */
    private Money totalAmount;
    /**
     * 订单状态
     * 
     * @see com.example.orderddd.domain.model.valueobject.OrderStatus
     */
    private OrderStatus status = OrderStatus.PENDING;
    /**
     * 订单项
     */
    private List<OrderItem> items;

    /**
     * 是否使用储值卡
     */
    private Boolean useStoredValueCard;

    // 取消订单
    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
    }
}
