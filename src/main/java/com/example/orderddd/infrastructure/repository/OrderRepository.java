package com.example.orderddd.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.orderddd.domain.model.aggregate.Order;
import com.example.orderddd.domain.model.entity.OrderItem;
import com.example.orderddd.infrastructure.repository.mapper.OrderItemMapper;
import com.example.orderddd.infrastructure.repository.mapper.OrderMapper;

/**
 * 订单仓储类
 * 
 * @author lejb
 * @version 1.0
 */
@Repository
public class OrderRepository extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    private OrderItemMapper orderItemMapper;

    public Order findById(String orderId) {
        return getById(orderId);
    }

    @Transactional
    public void saveOrder(Order order) {
        //保存order
        save(order);

        // 保存 OrderItems
        for (OrderItem item : order.getItems()) {
            item.setOrderId(order.getOrderId()); // 设置双向关联
            orderItemMapper.insert(item);
        }
    }
}
