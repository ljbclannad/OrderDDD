package com.example.orderddd.infrastructure.config.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DelayMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelayMessage(String message, long delay) {
        CorrelationData correlationData = new CorrelationData();
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_DELAY_EXCHANGE_NAME,
                RabbitMQConfig.ORDER_DELAY_ROUTING_KEY,
                message,
                messagePostProcessor -> {
                    return messagePostProcessor;
                },
                correlationData);
        
        log.info("延时消息已发送: exchange={}, routingKey={}, message={}, id={}", 
                RabbitMQConfig.ORDER_DELAY_EXCHANGE_NAME, 
                RabbitMQConfig.ORDER_DELAY_ROUTING_KEY, 
                message, correlationData.getId());
    }
}
