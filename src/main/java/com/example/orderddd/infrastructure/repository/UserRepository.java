package com.example.orderddd.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.orderddd.domain.model.aggregate.User;
import com.example.orderddd.infrastructure.repository.mapper.UserMapper;

@Repository
public class UserRepository extends ServiceImpl<UserMapper, User> {

    public User findById(String userId) {
        return getById(userId);
    }
}
