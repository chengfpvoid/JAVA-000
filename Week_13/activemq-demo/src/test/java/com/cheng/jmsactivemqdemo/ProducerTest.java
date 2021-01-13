package com.cheng.jmsactivemqdemo;

import com.cheng.jmsactivemqdemo.message.Message;
import com.cheng.jmsactivemqdemo.producer.QueueProducer;
import com.cheng.jmsactivemqdemo.producer.TopicProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class ProducerTest {

    @Autowired
    private QueueProducer queueProducer;

    @Autowired
    private TopicProducer topicProducer;

    @Test
    public void testQueueModelSend() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            long id = System.currentTimeMillis();
            String content = "hello queue message:" + id;
            Message message = new Message();
            message.setId(id);
            message.setContent(content);
            queueProducer.sendMessage(message);
        }
        new CountDownLatch(1).await();
    }

    @Test
    public void testTopicModelSend() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            long id = System.currentTimeMillis();
            String content = "hello topic message:" + id;
            Message message = new Message();
            message.setId(id);
            message.setContent(content);
            topicProducer.sendMessage(message);
        }
        new CountDownLatch(1).await();
    }



}
