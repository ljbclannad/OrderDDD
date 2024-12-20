package com.example.orderddd.domain.model.entity;

import com.example.orderddd.domain.model.valueobject.Money;

import lombok.Data;

/**
 * 实体-储值卡
 * 
 * @author lejb
 * @version 1.0
 */
@Data
public class StoredValueCard {
    /**
     * 储值卡ID
     */
    private String cardId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 储值金额
     */
    private Money storedValue;
    /**
     * 是否已使用
     */
    private Boolean isUsed;
}
