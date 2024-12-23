# Getting Started

## Reference Documentation

* [Build architecture](doc/构建架构.md)

## 项目结构

```
/OrderDDD
│
├── /src
│   ├── /main
│   │   ├── /java
│   │   │   └── /com
│   │   │       └── example
│   │   │           └── orderddd
│   │   │               ├── OrderDddApplication.java
│   │   │               ├── /applicationservice # 应用服务
│   │   │               │   └── OrderDddApplicationService.java
│   │   │               │   └── UserApplicationService.java
│   │   │               ├── /domain # 领域模块
│   │   │               │   ├── /model # 领域模型
│   │   │               │   │   ├── /aggregate
│   │   │               │   │   │   ├── Order.java          # 聚合根
│   │   │               │   │   │   └── User.java           # 聚合根
│   │   │               │   │   ├── /entity
│   │   │               │   │   │   ├── OrderItem.java      # 实体
│   │   │               │   │   │   └── Product.java        # 实体
│   │   │               │   │   └── /valueobject
│   │   │               │   │       ├── Money.java          # 值对象
│   │   │               │   │       └── Address.java        # 值对象
│   │   │               │   ├── /service # 领域服务
│   │   │               │   │   └── OrderDomainService.java
│   │   │               │   │   └── UserDomainService.java
│   │   │               │   │   └── ProductDomainService.java
│   │   │               │   └── /exception # 自定义异常
│   │   │               │       └── InsufficientStockException.java
│   │   │               ├── /infrastructure # 基础设施
│   │   │               │   └── /repository # 持久化仓储
│   │   │               │   │   └── UserRepository.java
│   │   │               │   │   └── OrderRepository.java
│   │   │               │   │   └── ProductRepository.java
│   │   │               │   └── /config # 配置
│   │   │               │   │   └── MyBatisConfig.java
│   │   │               │   └── /rabbitmq # rabbitmq配置
│   │   │               │   │   └── RabbitMQConfig.java
│   │   │               │   │   └── RabbitMessageSender.java
│   │   │               │   │   └── MessageReceiver.java
│   │   │               │   │   └── DelayMessageSender.java
│   │   │               │   └── /validator # 验证器
│   │   │               │   │   └── /jwt # 配置
│   │   │               │   │   │   └── JwtConfig.java
│   │   │               │   │   │   └── JwtRequestFilter.java
│   │   │               │   │   │   └── SecurityConfig.java
│   │   │               ├── /interfaces # 接口
│   │   │               │   └── /rest
│   │   │               │       └── OrderController.java
│   │   │               │   └── /remote # 远程调用
│   │   │               │       └── PaymentClient.java
│   │   │               │       └── AliPaymentClient.java
│   │   │               │       └── WeChatPaymentClient.java
│   │   │               ├── /anticorruption # 防腐层
│   │   │               │   ├── /cqrs 
│   │   │               │       └── PayOrderCommand.java
│   │   │               │       └── CancelOrderCommand.java
│   │   │               │   └── /payment # 支付防腐层
│   │   │               │       └── PayFactory.java
│   │   │               │       └── AliPay.java
│   │   │               │       └── WeChatPay.java
│   │   ├── /resources
│   │   │   └── application.yaml  # 配置文件
│   │   └── /test
│   │       └── /java
│   │           └── /com
│   │               └── example
│   │                   └── orderddd
│   │                       └── OrderServiceTest.java
│   └── /docs
│       └── architecture.md  # 项目架构文档
└── pom.xml  # Maven 或 Gradle 配置文件
```