# 作业记录

### 周四必做作业：设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表

通过shardingsphere-jdbc进行分库分表，根据userId%2分库 orderId%16分表。

代码链接：https://github.com/chengfpvoid/JAVA-000/tree/main/Week_08/sharding-jdbc-sharding-database-tables

### 周六比做作业：基于hmily TCC或ShardingSphere的Atomikos XA实现一个简单的分布式 事务应用demo（二选一），提交到github。 

通过shardingsphere-jdbc-xa 配置XA分布式事务。

1. 排除`@SpringBootApplication(exclude = JtaAutoConfiguration.class)`
2. 通过`@ShardingTransactionType(value = TransactionType.XA)` 指定分布式事务类型为XA

代码链接：https://github.com/chengfpvoid/JAVA-000/tree/main/Week_08/sharding-jdbc-sharding-xa