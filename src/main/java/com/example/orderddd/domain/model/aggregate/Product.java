package com.example.orderddd.domain.model.aggregate;

import com.example.orderddd.domain.model.valueobject.Money;

import lombok.Data;

/**
 * 聚合根-商品
 * 把商品作为聚合根是由于商品本身存在复杂业务逻辑，需要频繁修改库存，价格
 * 
 * @author lejb
 * @version 1.0
 */
@Data
public class Product {
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品价格
     */
    private Money price;
    /**
     * 商品库存
     */
    private int stock;

    // 扣减库存
    public void subtractProduct(int quantity) {
        this.stock -= quantity;
    }

    // 增加库存
    public void addProduct(int quantity) {
        this.stock += quantity;
    }
}
