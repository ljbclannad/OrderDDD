package com.example.orderddd.domain.model.aggregate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.orderddd.domain.model.valueobject.Address;
import com.example.orderddd.domain.model.valueobject.Email;
import com.example.orderddd.domain.model.valueobject.Money;
import com.example.orderddd.domain.model.valueobject.PhoneNumber;

import lombok.Data;

/**
 * 聚合根-用户
 * 
 * @author lejb
 * @version 1.0
 */
@TableName("public.user")
@Data
public class User {
    /**
     * 用户ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    public Integer userId;
    /**
     * 用户名
     */
    public String name;
    /**
     * 用户邮箱
     */
    public Email email;
    /**
     * 用户手机号
     */
    public PhoneNumber phoneNumber;
    /**
     * 用户地址
     */
    @TableField(exist = false)
    public Address address;
    /**
     * 用户储值金额
     */
    @TableField(exist = false)
    public Money storedValues;

    public void deductStoredValue(Money amount) {
        this.storedValues = storedValues.subtract(amount);
    }

    public void addStoredValue(Money amount) {
        this.storedValues = storedValues.add(amount);
    }

}
