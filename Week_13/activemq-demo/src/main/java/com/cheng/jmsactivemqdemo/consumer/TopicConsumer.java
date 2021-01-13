package com.cheng.jmsactivemqdemo.consumer;

import com.cheng.jmsactivemqdemo.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * topic模式消费者
 * @author Cheng
 */
@Component
@Slf4j
public class TopicConsumer {
    /**
     * topic模式消息监听回调
     * @param message 接收的消息
     */
    @JmsListener(destination = Message.TOPIC_NAME,containerFactory = "topicJmsListenerContainerFactory")
    public void onMessage(Message message) {
        log.info("[onMessage] thread-id:{} message :",Thread.currentThread().getId(),message);
    }
}
