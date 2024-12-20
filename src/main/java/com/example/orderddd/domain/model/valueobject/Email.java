package com.example.orderddd.domain.model.valueobject;

import lombok.Data;

/**
 * 值对象-邮箱
 * 
 * @author lejb
 * @version 1.0
 */
@Data
public class Email {
    private final String email;

    public Email(String email) {
        if (!isValid(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    private boolean isValid(String email) {
        // 验证电子邮件格式的逻辑
        return email.contains("@");
    }
}
