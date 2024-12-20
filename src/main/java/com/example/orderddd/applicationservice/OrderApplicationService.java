package com.example.orderddd.applicationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderddd.anticorruption.cqrs.commond.CreateOrder;
import com.example.orderddd.anticorruption.payment.request.PaymentRequestFactory;
import com.example.orderddd.anticorruption.payment.response.PaymentResponseFactory;
import com.example.orderddd.domain.model.aggregate.Order;
import com.example.orderddd.domain.model.aggregate.User;
import com.example.orderddd.domain.service.OrderDomainService;
import com.example.orderddd.domain.service.ProductDomainService;
import com.example.orderddd.domain.service.UserDomainService;
import com.example.orderddd.infrastructure.repository.OrderRepository;
import com.example.orderddd.infrastructure.repository.ProductRepository;
import com.example.orderddd.infrastructure.repository.UserRepository;
import com.example.orderddd.interfaces.remote.PaymentClient;

/**
 * 订单应用服务
 * 处理订单的业务逻辑
 * 
 * @author lejb
 * @version 1.0
 */
@Service
public class OrderApplicationService {

    private OrderDomainService orderDomainService;

    @Autowired
    private OrderRepository orderRepository;

    private UserDomainService userDomainService;

    @Autowired
    private UserRepository userRepository;

    private ProductDomainService productDomainService;

    @Autowired
    private ProductRepository productRepository;

    private PaymentClient paymentClient;

    // 创建订单
    public void createOrder(CreateOrder createOrderCommand) {
        // 订单领域服务组装订单聚合根逻辑
        Order order = orderDomainService.createOrder(createOrderCommand);
        // 保存订单
        orderRepository.saveOrder(order);
        // 如果使用储值卡，则从储值卡中扣除金额
        if (createOrderCommand.getUseStoredValueCard()) {
            // 调用储值卡服务
            // userDomainService.deductStoredValueCard(createOrderCommand.getUserId(),
            // createOrderCommand.getTotalAmount());
        } else {
            // 调用第三方支付（防腐层）
            PaymentRequestFactory paymentRequestFactory = new PaymentRequestFactory();
            PaymentResponseFactory paymentResponseFactory = paymentClient.pay(paymentRequestFactory);
        }

    }

}
