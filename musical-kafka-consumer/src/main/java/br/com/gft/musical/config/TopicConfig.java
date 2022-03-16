package br.com.gft.musical.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TopicConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddressMessage;

    @Bean
    public KafkaAdmin kafkaAdminMessage() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddressMessage);
        return new KafkaAdmin(configs);
    }
}
