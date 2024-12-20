package com.example.orderddd.infrastructure.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.orderddd.domain.model.entity.OrderItem;

/**
 * 订单项Mapper
 * 
 * @author lejb
 * @version 1.0
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
