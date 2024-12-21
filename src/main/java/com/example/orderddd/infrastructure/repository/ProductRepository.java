package com.example.orderddd.infrastructure.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.orderddd.domain.model.aggregate.Product;
import com.example.orderddd.infrastructure.repository.mapper.ProductMapper;

@Repository
public class ProductRepository extends ServiceImpl<ProductMapper, Product> {

    // 扣减库存
    public void subtractProduct(ConcurrentHashMap<String, Integer> productCount) {
        productCount.forEach((productId, quantity) -> {
            Product product = this.getById(productId);
            product.subtractProduct(quantity);
            this.updateById(product);
        });
    }

    // 增加库存
    public void addProduct(ConcurrentHashMap<String, Integer> productCount) {
        productCount.forEach((productId, quantity) -> {
            Product product = this.getById(productId);
            product.addProduct(quantity);
            this.updateById(product);
        });
    }
}
