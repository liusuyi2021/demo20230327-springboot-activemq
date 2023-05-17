package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class Demo20230327SpringbootActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo20230327SpringbootActivemqApplication.class, args);
    }

}
