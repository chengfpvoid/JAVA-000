## **作业 ：读写分离 - 动态切换数据源版本 1.0**

使用继承`AbstractRoutingDataSource`方式选择数据源。

通过实现`ImportBeanDefinitionRegistrar`接口，将配置信息导入，动态的注册数据源，并将他们配置到Map中，

配置Ds注解通过AOP的方式，讲Ds的注解的vlaue作为key，去获取当前线程所需要的数据源。

