package com.example.orderddd.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderddd.infrastructure.config.rabbitmq.DelayMessageSender;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // @Autowired
    // private OrderApplicationService orderApplicationService;

    @Autowired
    private DelayMessageSender delayMessageSender;

    @GetMapping("/delay")
    public void delay() {
        delayMessageSender.sendDelayMessage("test", 100);
    }
}
