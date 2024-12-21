package com.example.orderddd.mq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.orderddd.infrastructure.config.rabbitmq.DelayMessageSender;

@SpringBootTest
public class OrderMQTest {

    @Autowired
    private DelayMessageSender delayMessageSender;

    @Test
    public void testOrderMQ() {
        delayMessageSender.sendDelayMessage("test", 10000);
        System.out.println("Message sent");
    }
}
