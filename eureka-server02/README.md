<h1># UID Spring Cloud  #</h1>
<h2># Spring Cloud</h2>
Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署
<h2>#五大重要组件</h2>
服务发现——Netflix Eureka </br>
客服端负载均衡——Netflix Ribbon/Feign </br>
服务网关——Netflix Zuul </br>
断路器——Netflix Hystrix </br>
分布式配置——Spring Cloud Config

<h3>#1.Spring cloud-Eureka注册中心</h3>
Eureka是netflix的一个子模块，也是核心模块之一，Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移，服务注册与发现对于微服务架构来说是非常重要的，有了服务发现和注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务，而不需要修改服务调用的配置文件了

