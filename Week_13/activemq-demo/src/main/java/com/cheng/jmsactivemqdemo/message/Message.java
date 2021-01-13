package com.cheng.jmsactivemqdemo.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息实体
 * @author Cheng
 */
@Data
public class Message implements Serializable {

    public static final String QUEUE_NAME = "queue_model_test";

    public static final String TOPIC_NAME = "queue_topic_test";

    private static final long serialVersionUID = 1L;

    private Long id;

    private String content;
}
