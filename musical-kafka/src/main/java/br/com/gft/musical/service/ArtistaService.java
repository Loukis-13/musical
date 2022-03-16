package br.com.gft.musical.service;

import br.com.gft.musical.api.artista.model.Artista;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService {
    @Autowired
    private SendToKafka sendMessage;


    public void processMessage(String message) {
        try {
            sendMessage.sendMessage(message);
        }catch (KafkaException e) {
            e.printStackTrace();
        }
    }

    public void sendArtistaToKafka(Artista artista) {
        try {
            processMessage(JacksonUtils.enhancedObjectMapper().writeValueAsString(artista));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
