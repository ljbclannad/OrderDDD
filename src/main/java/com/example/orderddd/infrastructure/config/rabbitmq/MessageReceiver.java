package com.example.orderddd.infrastructure.config.rabbitmq;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.example.orderddd.anticorruption.cqrs.commond.PayOrderCommand;
import com.example.orderddd.applicationservice.OrderApplicationService;
import com.rabbitmq.client.Channel;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageReceiver {

    @Autowired
    private OrderApplicationService orderApplicationService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_DEAD_LETTER_QUEUE_NAME, ackMode = "MANUAL")
    public void receiveTimeoutMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("Received message from delay queue: {}", message);
        try {
            // 设置该消费者的预取数量为5
            channel.basicQos(5);
            
            // 订单超时取消
            orderApplicationService.cancelOrder(message);
            //手动确认
            channel.basicAck(deliveryTag, false);
            log.info("消息确认成功: {}", message);
        } catch (Exception e) {
            log.error("取消订单失败", e);
            try {
                // 消息处理失败，拒绝消息并重新入队
                channel.basicNack(deliveryTag, false, true);
                log.warn("消息处理失败，已重新入队: {}", message);
            } catch (IOException ioException) {
                log.error("消息拒绝失败", ioException);
            }
        }
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_PAY_QUEUE_NAME)
    public void receivePayMessage(String message, Channel channel) throws IOException {
        log.info("Received message from pay queue: {}", message);
        
        // 设置该消费者的预取数量为10
        channel.basicQos(10);
        
        // 订单支付
        PayOrderCommand payOrderCommand = JSONUtil.toBean(message, PayOrderCommand.class);
        orderApplicationService.thirdPay(payOrderCommand);
    }

}
