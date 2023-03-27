package com.example.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpoint;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.concurrent.Executors;

/**
 * @ClassName BeanConfig
 * @Description:
 * @Author 刘苏义
 * @Date 2023/3/27 19:02
 * @Version 1.0
 */

@EnableJms
@Configuration
public class BeanConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    @Value("${spring.activemq.topic-name}")
    private String topicName;

    @Bean(name = "queue1")
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

    //如果有多个queue主题，则继续增加@Bean, @Bean内的name可省略,省略后name为方法名（"queue2"），
    //注意: 后续@Autowired注解“public Queue queue2;”的变量名为此处的name("queue2");
    //队列名称为"queueName2", 后续的队列消费时destination要和此队列名称一致。
    @Bean(name = "queue2")
    public Queue queue2() {
        return new ActiveMQQueue("queue2");
    }

    //其他的queue主题定义
    //...

    @Bean(name = "topic1")
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }

    @Bean(name = "topic2")
    public Topic topic2() {
        return new ActiveMQTopic("topic2");
    }

    //其他的topic主题定义
    //...

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    @Bean
    public JmsMessagingTemplate jmsMessageTemplate() {
        return new JmsMessagingTemplate(connectionFactory());
    }

    // 在Queue模式中，对消息的监听需要对containerFactory进行配置
    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    //在Topic模式中，对消息的监听需要对containerFactory进行配置
    @Bean("topicListener")
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}