package com.example.orderddd.infrastructure.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // 订单取消延时队列
    public static final String ORDER_DELAY_QUEUE_NAME = "orderDelayQueue";
    public static final String ORDER_DELAY_EXCHANGE_NAME = "orderDelayExchange";
    public static final String ORDER_DELAY_ROUTING_KEY = "orderDelayRoutingKey";
    // 订单死信队列
    public static final String ORDER_DEAD_LETTER_QUEUE_NAME = "orderDeadLetterQueue";

    // 订单支付队列
    public static final String ORDER_PAY_QUEUE_NAME = "orderPayQueue";
    public static final String ORDER_PAY_EXCHANGE_NAME = "orderPayExchange";
    public static final String ORDER_PAY_ROUTING_KEY = "orderPayRoutingKey";

    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder.durable(ORDER_DELAY_QUEUE_NAME)
                .withArgument("x-message-ttl", 1000)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", ORDER_DEAD_LETTER_QUEUE_NAME)
                .build();
    }

    @Bean
    public Queue orderDeadLetterQueue() {
        return new Queue(ORDER_DEAD_LETTER_QUEUE_NAME);
    }

    @Bean
    public TopicExchange orderDelayExchange() {
        return new TopicExchange(ORDER_DELAY_EXCHANGE_NAME);
    }

    @Bean
    public Binding orderDelayBinding() {
        return BindingBuilder.bind(orderDelayQueue()).to(orderDelayExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }

    @Bean
    public Queue orderPayQueue() {
        return new Queue(ORDER_PAY_QUEUE_NAME);
    }

    @Bean
    public TopicExchange orderPayExchange() {
        return new TopicExchange(ORDER_PAY_EXCHANGE_NAME);
    }

    @Bean
    public Binding orderPayBinding() {
        return BindingBuilder.bind(orderPayQueue()).to(orderPayExchange()).with(ORDER_PAY_ROUTING_KEY);
    }
}
