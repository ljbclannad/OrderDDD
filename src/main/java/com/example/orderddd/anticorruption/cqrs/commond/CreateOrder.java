package com.example.orderddd.anticorruption.cqrs.commond;



import com.example.orderddd.domain.model.aggregate.Order;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateOrder extends Order {


}
