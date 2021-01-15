package com.training.kafka.KafkaTraining.config.kafka;

import com.training.kafka.KafkaTraining.config.kafka.properties.KafkaProperties;
import com.training.kafka.KafkaTraining.config.kafka.util.KafkaProducerListener;
import com.training.kafka.KafkaTraining.enums.KafkaClientIdType;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static com.training.kafka.KafkaTraining.enums.KafkaClientIdType.JSON;
import static com.training.kafka.KafkaTraining.enums.KafkaClientIdType.STRING;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(getStringProducerConfigs()));
        kafkaTemplate.setProducerListener(new KafkaProducerListener<>());

        return kafkaTemplate;
    }

    private Map<String, Object> getStringProducerConfigs() {
        return new HashMap<>(getDefaultProducerConfigs(STRING)) {{
            put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        }};
    }

    @Bean
    public KafkaTemplate<String, Object> jsonKafkaTemplate() {
        KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(getJsonProducerConfigs()));
        kafkaTemplate.setProducerListener(new KafkaProducerListener<>());

        return kafkaTemplate;
    }

    private Map<String, Object> getJsonProducerConfigs() {
        return new HashMap<>(getDefaultProducerConfigs(JSON)) {{
            put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        }};
    }

    private Map<String, Object> getDefaultProducerConfigs(KafkaClientIdType type) {
        return Map.ofEntries(
                Map.entry(BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers()),
                Map.entry(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                Map.entry(CLIENT_ID_CONFIG, kafkaProperties.getClient().getId().concat("-").concat(type.name().toLowerCase()))
        );
    }
}
