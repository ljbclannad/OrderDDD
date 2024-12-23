package com.example.orderddd.domain.service;

import com.example.orderddd.domain.model.aggregate.User;
import com.example.orderddd.domain.model.valueobject.Money;

/**
 * 用户领域服务-仅操作用户相关逻辑
 * 
 * @author lejb
 * @version 1.0
 */
public class UserDomainService {

    public User getUser(String userId) {
        // 获取用户信息
        return null;
    }

    /**
     * 扣除用户储值金额
     * 
     * @param amount 金额
     * @param user   用户
     * @return 新用户
     */
    public User deductStoredValue(Money amount, User user) {
        return user.deductStoredValue(amount);
    }
}
