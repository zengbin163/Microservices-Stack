<h1># UID Spring Cloud  #</h1>
<h2># Spring Cloud</h2>
Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署
<h2>#五大重要组件</h2>
服务发现——Netflix Eureka </br>
客服端负载均衡——Netflix Ribbon/Feign </br>
服务网关——Netflix Zuul </br>
断路器——Netflix Hystrix </br>
分布式配置——Spring Cloud Config

<h3>#1.Spring cloud-Zuul微服务网关</h3>
zuul的核心是一系列的filters, 其作用类比Servlet框架的Filter，或者AOP。 </br>
zuul把请求路由到用户处理逻辑的过程中，这些filter参与一些过滤处理，比如Authentication，Load Shedding等  </br>
<h3>Zuul使用一系列不同类型的过滤器，使我们能够快速灵活地将功能应用于我们的边缘服务。这些过滤器可帮助我们执行以下功能</h3>
· 身份验证和安全性 - 确定每个资源的身份验证要求并拒绝不满足这些要求的请求 </br>
· 洞察和监控 - 在边缘跟踪有意义的数据和统计数据，以便为我们提供准确的生产视图 </br>
· 动态路由 - 根据需要动态地将请求路由到不同的后端群集 </br>
· 压力测试 - 逐渐增加群集的流量以衡量性能。 </br>
· Load Shedding - 为每种类型的请求分配容量并删除超过限制的请求 </br>
· 静态响应处理 - 直接在边缘构建一些响应，而不是将它们转发到内部集群 </br>
<h3>zuul组件</h3>
· zuul-core--zuul核心库，包含编译和执行过滤器的核心功能。 </br>
· zuul-simple-webapp--zuul Web应用程序示例，展示了如何使用zuul-core构建应用程序。 </br>
· zuul-netflix--lib包，将其他NetflixOSS组件添加到Zuul中，例如使用功能区进去路由请求处理。 </br>
· zuul-netflix-webapp--webapp，它将zuul-core和zuul-netflix封装成一个简易的webapp工程包。 </br>

熔断，降级，限流，排队