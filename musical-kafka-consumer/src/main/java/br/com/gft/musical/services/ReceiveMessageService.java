package br.com.gft.musical.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiveMessageService {
    @KafkaListener(topics = "${spring.kafka.topic.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        System.out.println(message);
    }
}
