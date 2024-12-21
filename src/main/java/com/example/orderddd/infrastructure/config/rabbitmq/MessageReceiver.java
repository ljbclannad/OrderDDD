package com.example.orderddd.infrastructure.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.orderddd.applicationservice.OrderApplicationService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageReceiver {

    @Autowired
    private OrderApplicationService orderApplicationService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_DEAD_LETTER_QUEUE_NAME)
    public void receiveTimeoutMessage(String message) {
        log.info("Received message from delay queue: {}", message);
        // 订单超时取消
        orderApplicationService.cancelOrder(message);
    }

}
