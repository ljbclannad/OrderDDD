package com.example.orderddd.domain.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.example.orderddd.domain.model.entity.OrderItem;

/**
 * 产品领域服务-仅操作产品相关逻辑
 * 
 * @author lejb
 * @version 1.0
 */
public class ProductDomainService {

    public ConcurrentHashMap<String, Integer> getProductCount(List<OrderItem> items) {
        ConcurrentHashMap<String, Integer> productCount = new ConcurrentHashMap<>();
        items.forEach(item -> {
            productCount.put(item.getProductId(),
                    productCount.getOrDefault(item.getProductId(), 0) + item.getQuantity());
        });
        return productCount;
    }

}
