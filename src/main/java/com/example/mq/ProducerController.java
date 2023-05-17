package com.example.mq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;
/**
 * @ClassName ProducerController
 * @Description:
 * @Author 刘苏义
 * @Date 2023/3/27 19:04
 * @Version 1.0
 */

@RestController
public class ProducerController
{
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue1;

    @Autowired
    private Queue queue2;

    //更多的Queue...

    @Autowired
    private Topic topic1;
    @Autowired
    private Topic topic2;
    //更多的topic...

    @PostMapping("/queue/test")
    public String sendQueue(@RequestBody String str) {
        this.sendMessage(this.queue1, str);
        return "success";
    }

    @PostMapping("/queue2/test")
    public String sendQueue2(@RequestBody String str) {
        this.sendMessage(this.queue2, str);
        return "queue2 success";
    }
    @PostMapping("/topic/test")
    public String sendTopic(@RequestBody String str) {
        this.sendMessage(this.topic1, str);
        return "success";
    }
    @PostMapping("/topic2/test")
    public String sendTopic2(@RequestBody String str) {
        this.sendMessage(this.topic2, str);
        return "topic2 success";
    }
    // 发送消息，destination是发送到的队列，message是待发送的消息
    private void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}