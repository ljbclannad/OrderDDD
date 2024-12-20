package com.example.orderddd.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.orderddd.domain.model.aggregate.Product;
import com.example.orderddd.infrastructure.repository.mapper.ProductMapper;

@Repository
public class ProductRepository extends ServiceImpl<ProductMapper, Product> {

}
