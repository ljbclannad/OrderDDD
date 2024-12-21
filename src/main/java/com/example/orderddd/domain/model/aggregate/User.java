package com.example.orderddd.domain.model.aggregate;



import com.example.orderddd.domain.model.valueobject.Address;
import com.example.orderddd.domain.model.valueobject.Email;
import com.example.orderddd.domain.model.valueobject.Money;
import com.example.orderddd.domain.model.valueobject.PhoneNumber;

/**
 * 聚合根-用户
 * 
 * @author lejb
 * @version 1.0
 */
public record User (
    /**
     * 用户ID
     */
    String userId,
    /**
     * 用户名
     */
    String name,
    /**
     * 用户邮箱
     */
    Email email,
    /**
     * 用户手机号
     */
    PhoneNumber phoneNumber,
    /**
     * 用户地址
     */
    Address address,
    /**
     * 用户储值金额
     */
    Money storedValues
) {
    public User deductStoredValue(Money amount) {
        Money newStoredValues = storedValues.subtract(amount);
        return new User(userId, name, email, phoneNumber, address, newStoredValues);
    }

    public User addStoredValue(Money amount) {
        Money newStoredValues = storedValues.add(amount);
        return new User(userId, name, email, phoneNumber, address, newStoredValues);
    }
}
