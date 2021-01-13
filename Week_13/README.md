### 必做作业1  activemq

> 搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费

必做作业1的代码链接：https://github.com/chengfpvoid/JAVA-000/tree/main/Week_13/activemq-demo

[ActiveMqConfig.java](https://github.com/chengfpvoid/JAVA-000/blob/main/Week_13/activemq-demo/src/main/java/com/cheng/jmsactivemqdemo/config/ActiveMqConfig.java) 配置了activemq的queue与topic的jmsTemplate和 linstener container factory

producer包下面分别定义了queue和topic两种类型的生产者

message包里面 定义了消息实体

counsumer包 下面分别监听和消费queue和topic两种类型的消息

[ProducerTest.java](https://github.com/chengfpvoid/JAVA-000/blob/main/Week_13/activemq-demo/src/test/java/com/cheng/jmsactivemqdemo/ProducerTest.java)  测试了两种不同模式的消息发送。