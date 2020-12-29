package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)//将信道channel和exchange绑定在一起
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)//监听队列，用于消费者的队列的消息接收
    public void input(Message<String> message) {
      System.out.println("消费者1号---------->接收到消息："+message.getPayload()+"\t port: "+serverPort);
    }
}
