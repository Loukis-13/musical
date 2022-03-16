package br.com.gft.musical.service;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SendToKafka {
    @Value(value = "${original.alcd.kafka.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String msg) {
        CompletableFuture<SendResult<String, String>> sendKafka = this.kafkaTemplate.send(topicName, msg).completable();
        sendKafka.whenComplete((res, ex) -> {
            if (ex != null) {
                throw new KafkaException(ex.getMessage());
            }
        });
    }
}
