package com.example.orderddd.infrastructure.config.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelayMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelayMessage(String message, long delay) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_DELAY_EXCHANGE_NAME,
                RabbitMQConfig.ORDER_DELAY_ROUTING_KEY,
                message,
                messagePostProcessor -> {
                    return messagePostProcessor;
                });
    }
}
