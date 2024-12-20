package com.example.orderddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.orderddd.infrastructure.repository.mapper"})
//TODO 设计一套主从数据库
public class OrderDddApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderDddApplication.class, args);
    }

}
