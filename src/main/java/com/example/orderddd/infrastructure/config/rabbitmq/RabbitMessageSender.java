package com.example.orderddd.infrastructure.config.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @PostConstruct
    public void init() {
        // 启用发布确认
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String id = correlationData != null ? correlationData.getId() : "";
            if (ack) {
                log.info("消息发送成功: id={}", id);
            } else {
                log.error("消息发送失败: id={}, cause={}", id, cause);
            }
        });
        
        // 启用发布返回（当消息无法路由到队列时触发）
        rabbitTemplate.setReturnsCallback(returned -> {
            log.error("消息无法路由: exchange={}, routingKey={}, replyCode={}, replyText={}, message={}",
                    returned.getExchange(), returned.getRoutingKey(), 
                    returned.getReplyCode(), returned.getReplyText(), 
                    new String(returned.getMessage().getBody()));
        });
        
        // 必须设置为true，否则消息无法路由时不会触发ReturnsCallback回调
        rabbitTemplate.setMandatory(true);
    }

    public void sendMessage(String exchange, String routingKey, String message) {
        CorrelationData correlationData = new CorrelationData();
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
        log.info("消息已发送: exchange={}, routingKey={}, message={}, id={}", 
                exchange, routingKey, message, correlationData.getId());
    }

}
