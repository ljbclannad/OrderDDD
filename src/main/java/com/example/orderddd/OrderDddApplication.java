package com.example.orderddd;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@MapperScan(basePackages = { "com.example.orderddd.infrastructure.repository.mapper" })
@EnableScheduling
public class OrderDddApplication {

    private static final Logger logger = LoggerFactory.getLogger(OrderDddApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderDddApplication.class, args);
        logger.info("Spring Boot application has started.");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledTask() {
        logger.info("Scheduled task is running." + System.currentTimeMillis());
    }

}
