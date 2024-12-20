package com.example.orderddd.infrastructure.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.orderddd.domain.model.aggregate.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
