package com.example.orderddd.domain.model.valueobject;

import lombok.Data;

/**
 * 值对象-地址
 * 
 * @author lejb
 * @version 1.0
 */
@Data
public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;
}
