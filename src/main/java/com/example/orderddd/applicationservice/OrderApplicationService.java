package com.example.orderddd.applicationservice;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.orderddd.anticorruption.cqrs.commond.CreateOrderCommand;
import com.example.orderddd.anticorruption.cqrs.commond.PayOrderCommand;
import com.example.orderddd.anticorruption.payment.PayFactory;
import com.example.orderddd.domain.model.aggregate.Order;
import com.example.orderddd.domain.model.aggregate.User;
import com.example.orderddd.domain.model.valueobject.OrderStatus;
import com.example.orderddd.domain.service.OrderDomainService;
import com.example.orderddd.domain.service.ProductDomainService;
import com.example.orderddd.domain.service.UserDomainService;
import com.example.orderddd.infrastructure.config.rabbitmq.DelayMessageSender;
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
@Transactional
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

    @Autowired
    private DelayMessageSender delayMessageSender;

    /**
     * 创建订单
     * 
     * @param createOrderCommand
     */
    @Transactional
    public void createOrder(CreateOrderCommand createOrderCommand) {
        // 订单领域服务组装订单聚合根逻辑
        Order order = orderDomainService.createOrder(createOrderCommand);
        // 保存订单
        orderRepository.saveOrder(order);
        // 扣减库存
        ConcurrentHashMap<String, Integer> productCount = productDomainService
                .getProductCount(createOrderCommand.getItems());
        productRepository.subtractProduct(productCount);
        // 延时队列添加订单
        delayMessageSender.sendDelayMessage(order.getOrderId().toString(), 10000);
    }

    /**
     * 取消订单
     * 
     * @param orderId
     */
    @Transactional
    public void cancelOrder(String orderId) {
        // 获取订单
        Order order = orderRepository.findOrderDetailById(orderId);
        // 如果订单不为待支付状态，直接结束逻辑
        if (order.getStatus() != OrderStatus.PENDING) {
            return;
        }
        order.cancelOrder();
        // 保存订单
        orderRepository.updateById(order);
        // 添加库存
        ConcurrentHashMap<String, Integer> productCount = productDomainService
                .getProductCount(order.getItems());
        productRepository.addProduct(productCount);
    }

    // 支付订单
    public void payOrder(PayOrderCommand payOrderCommand) {

        Order order = orderRepository.findById(payOrderCommand.getOrderId());
        User user = userRepository.findById(order.getUserId());
        boolean thirdPay = false;
        if (order.getUseStoredValueCard()) {
            if (user.storedValues().getAmount().compareTo(order.getTotalAmount().getAmount()) < 0) {
                // 调用储值卡服务
                user = userDomainService.deductStoredValue(user.storedValues(), user);
                thirdPay = true;
            } else {
                user = userDomainService.deductStoredValue(order.getTotalAmount(), user);
            }
        } else {
            thirdPay = true;
        }

        // 事件异步通知调用第三方支付
        if (thirdPay) {
            PayFactory payFactory = PayFactory.getPayFactory(payOrderCommand.getPayType());
            payFactory = PaymentClient.getPaymentClient(payFactory).pay(payFactory);
        }

        // 保存用户
        userRepository.updateById(user);
        // 保存订单
        orderRepository.updateById(order);
    }

}
