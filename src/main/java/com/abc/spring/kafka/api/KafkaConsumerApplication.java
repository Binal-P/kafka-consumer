package com.abc.spring.kafka.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

    List<String> messages =new ArrayList<>();

    User userFromTopic = null;

    @GetMapping("/consumeStringMessage")
    public List<String> consumeMessage(){
        return messages;
    }

    @GetMapping("/consumeJsonMessage")
    public User consumeJsonMessage(){
         return userFromTopic;
    }

    @KafkaListener(groupId = "javagroup-1",topics="javatopic", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromTopic(String data){
        messages.add(data);
        return messages;
    }

    @KafkaListener(groupId = "javagroup-2", topics = "javatopic", containerFactory = "userKafkaListenerContainerFactory")
    public User getUsrFromTopic(User userData){
        userFromTopic = userData;
        return userFromTopic;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

}
