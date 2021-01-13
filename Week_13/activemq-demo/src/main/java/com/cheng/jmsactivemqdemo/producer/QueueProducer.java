package com.cheng.jmsactivemqdemo.producer;

import com.cheng.jmsactivemqdemo.message.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 队列模式生产者
 */
@Component
public class QueueProducer {

    private JmsMessagingTemplate jmsTemplate;

    public QueueProducer(@Qualifier("queueJmsTemplate") JmsMessagingTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    /**
     * 发送消息
     * @param message 消息实体
     */
    public void sendMessage(Message message) {
        jmsTemplate.convertAndSend(Message.QUEUE_NAME,message);

    }
}
