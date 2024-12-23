package com.example.orderddd.infrastructure.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class OrderScheduled {

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled() {
    }
}
