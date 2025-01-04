package com.example.orderddd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = { "com.example.orderddd.infrastructure.repository.mapper" })
@EnableScheduling
// TODO 设计一套主从数据库
public class OrderDddApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderDddApplication.class, args);
    }

}
