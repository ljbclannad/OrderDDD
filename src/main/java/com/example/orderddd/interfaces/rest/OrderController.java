package com.example.orderddd.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderddd.applicationservice.OrderApplicationService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderApplicationService orderApplicationService;

    

}
