package com.example.orderddd.domain.model.valueobject;

import lombok.Data;

/**
 * 值对象-电话号码
 * 
 * @author lejb
 * @version 1.0
 */
@Data
public class PhoneNumber {
    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        if (!isValid(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phoneNumber = phoneNumber;
    }

    private boolean isValid(String phoneNumber) {
        // 验证电话号码格式的逻辑
        return phoneNumber.matches("\\d{10}");
    }
}
