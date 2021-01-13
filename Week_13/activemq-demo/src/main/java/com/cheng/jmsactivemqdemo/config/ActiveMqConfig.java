package com.cheng.jmsactivemqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

/**
 * activemq配置类
 * @author Cheng
 */
@Configuration
public class ActiveMqConfig {
    /**
     * 队列模式factory
     * @param configurer 自动配置类
     * @param connectionFactory activemq connection factory
     * @return DefaultJmsListenerContainerFactory对象
     */
    @Bean("queueJmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory queueJmsListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                                               ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        //非订阅模式 即队列模式
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean("queueJmsTemplate")
    public JmsMessagingTemplate queueJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(false);
        return new JmsMessagingTemplate(jmsTemplate);
    }


    /**
     * topic模式factory
     * @param configurer 自动配置类
     * @param connectionFactory activemq connection factory
     * @return DefaultJmsListenerContainerFactory对象
     */
    @Bean("topicJmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory topicJmsListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                                               ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        //订阅模式
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean("topicJmsTemplate")
    public JmsMessagingTemplate topicJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return new JmsMessagingTemplate(jmsTemplate);
    }


}
