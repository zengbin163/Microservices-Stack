<h1># 吃货美食  #</h1>
<h2># 待解决问题：</h2>
  1.集成百度UidGenerator spring包版本和springboot依赖的spring包版本兼容问题，所以UID生成需要单独暴露一个spring cloud服务<br/>
  2.事务处理、跨库事务处理（分布式事务处理）
<h2># 待实现任务：</h2>
  1.spring cloud微服务化 <br/>
  2.接入配置中心 <br/>
  3.接入消息中间件MQ <br/>
  4.接入网关API Gateway / ZUUL <br/>
  5.美食数据爬虫，爬取热门app美食信息 <br/>
  6.接入搜索引擎elasticsearch <br/>
<h2># 技术待观望</h2>  
  1.Service Mesh（服务网格） <br/>
  2.容器调度编排（kubernetes） <br/>
  3.容器虚拟化（docker） <br/>
 
<h3>基础的云平台为微服务提供了资源能力（计算、存储和网络等），容器作为最小工作单元被Kubernetes调度和编排，Service Mesh（服务网格）管理微服务的服务通信，最后通过API Gateway向外暴露微服务的业务接口。</h3>
<h3>使用Spring Cloud开发的应用程序非常适合在Docker和PaaS（比如Pivotal Cloud Foundry）上部署，所以又叫做云原生应用（Cloud Native Application）。云原生可以简单地理解为面向云环境的软件架构</h3>
<h3>Spring Cloud是一个基于Spring Boot实现的云原生应用开发工具，它为基于JVM的云原生应用开发中涉及的配置管理、服务发现、熔断器、智能路由、微代理、控制总线、分布式会话和集群状态管理等操作提供了一种简单的开发方式</h3>  