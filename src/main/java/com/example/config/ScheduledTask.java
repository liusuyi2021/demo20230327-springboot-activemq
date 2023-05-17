package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Topic;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName task
 * @Description:
 * @Author 刘苏义
 * @Date 2023/5/17 19:54
 * @Version 1.0
 */

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   //2.开启定时任务
public class ScheduledTask {
    @Autowired
    private Topic topic1;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    //@Scheduled(cron = "0/1 * * * * ?")
    @Scheduled(fixedRate=200)
    public void run() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.sendMessage(this.topic1, sdf.format(new Date()));
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
