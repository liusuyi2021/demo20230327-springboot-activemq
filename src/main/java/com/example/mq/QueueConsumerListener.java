package com.example.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName QueueConsumerListener
 * @Description:
 * @Author 刘苏义
 * @Date 2023/3/27 19:05
 * @Version 1.0
 */

@Component
@Slf4j
public class QueueConsumerListener {
    //queue1模式的消费者
    @JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
    @Async("mq")
    public void readActiveQueue1(String message) {
        log.info("queue接受到：" + message);
    }

    //queue2模式的消费者,  destination为queue主题，和ActiveMQBeanConfig定义中的@Bean中的"new ActiveMQQueue("queueName2");"的参数一致
    @JmsListener(destination = "queue2", containerFactory = "queueListener")
    public void readActiveQueue2(String message) {
        log.info("queue2接受到：" + message);
    }

    //更多的queue模式的消费者。。。

    //topic1模式的消费者
    @JmsListener(destination = "topic1", containerFactory = "topicListener")
    @Async("mq")
    public void receiveTopic1(String message) {
        log.info("topic1接收的消息是：" + message);
    }

    //topic2模式的消费者
    @JmsListener(destination = "topic2", containerFactory = "topicListener1")
    public void receiveTopic2(String message) {
        log.info("topic2接收的消息是：" + message);
    }

    //更多的topic模式的消费者。。。
}