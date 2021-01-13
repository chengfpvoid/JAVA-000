package com.cheng.jmsactivemqdemo.consumer;

import com.cheng.jmsactivemqdemo.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 队列模式消费者
 * @author Cheng
 */
@Component
@Slf4j
public class QueueConsumer {
    /**
     * 消息监听回调
     * @param message 接收的消息
     */
    @JmsListener(destination = Message.QUEUE_NAME,containerFactory = "queueJmsListenerContainerFactory")
    public void onMessage(Message message) {
        log.info("[onMessage] thread-id:{} message :",Thread.currentThread().getId(),message);
    }
}
