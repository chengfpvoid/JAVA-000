## **作业 ：读写分离 - 动态切换数据源版本 1.0**

使用继承`AbstractRoutingDataSource`方式选择数据源。

通过实现`ImportBeanDefinitionRegistrar`接口，将配置信息导入，动态的注册数据源，并将他们配置到Map中，

配置Ds注解通过AOP的方式，讲Ds的注解的vlaue作为key，去获取当前线程所需要的数据源。

代码链接 ：https://github.com/chengfpvoid/JAVA-000/tree/main/Week_07/abstractrouting-dynamic-datasource

## 作业 ：读写分离 2.0版本 使用ShardingSphere-jdbc 5.0.0-alpha 实现读写分离配置

参考shardingsphere官网和github 配置 shardingsphere-jdbc5.0.0-alpha版本的读写分离配置

这里有三处坑：

1. 必须配置负载均衡算法，不然会报错

2. 负载均衡算法必须配置props ,虽然官网说没有属性

3. Spring boot 2.3x版本报Caused by: java.util.NoSuchElementException: No value bound 

   参考 https://github.com/apache/shardingsphere/issues/8299 配置common属性得到解决。

代码链接：https://github.com/chengfpvoid/JAVA-000/tree/main/Week_07/sharding-jdbc-master-slave