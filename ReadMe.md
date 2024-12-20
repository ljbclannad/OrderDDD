# Getting Started

## Reference Documentation

* [Build architecture](doc/构建架构.md)

## 系统设计核心对象

* [model](doc/model.md)

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
│   │   │               │   │   └── OrderService.java
│   │   │               │   └── /exception # 自定义异常
│   │   │               │       └── InsufficientStockException.java
│   │   │               ├── /infrastructure # 基础设施
│   │   │               │   └── /repository # 持久化仓储
│   │   │               │   │   └── UserRepository.java
│   │   │               │   │   └── OrderRepository.java
│   │   │               │   │   └── ProductRepository.java
│   │   │               │   └── /config # 配置
│   │   │               │       └── MyBatisConfig.java
│   │   │               │   └── /validator # 验证器
│   │   │               │       └── OrderValidator.java
│   │   │               ├── /interfaces # 接口
│   │   │               │   └── /rest
│   │   │               │       └── OrderController.java
│   │   │               ├── /anticorruption # 防腐层
│   │   │               │   ├── /inventory # 库存防腐层
│   │   │               │   │   ├── InventoryService.java
│   │   │               │   │   └── ExternalInventoryServiceAdapter.java
│   │   │               │   └── /payment # 支付防腐层
│   │   │               │       ├── PaymentService.java
│   │   │               │       └── ExternalPaymentServiceAdapter.java
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