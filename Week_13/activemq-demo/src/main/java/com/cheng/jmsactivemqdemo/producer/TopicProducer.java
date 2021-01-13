package com.cheng.jmsactivemqdemo.producer;

import com.cheng.jmsactivemqdemo.message.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 队列模式生产者
 * @author Cheng
 */
@Component
public class TopicProducer {

    private JmsMessagingTemplate jmsTemplate;

    public TopicProducer(@Qualifier("topicJmsTemplate") JmsMessagingTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    /**
     * 发送消息
     * @param message 消息实体
     */
    public void sendMessage(Message message) {
        jmsTemplate.convertAndSend(Message.TOPIC_NAME,message);
    }
}
