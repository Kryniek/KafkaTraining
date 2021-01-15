package com.training.kafka.KafkaTraining.config.kafka;

import com.training.kafka.KafkaTraining.config.kafka.properties.KafkaProperties;
import com.training.kafka.KafkaTraining.enums.KafkaClientIdType;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.Map;

import static com.training.kafka.KafkaTraining.enums.KafkaClientIdType.JSON;
import static com.training.kafka.KafkaTraining.enums.KafkaClientIdType.STRING;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> stringKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
                new DefaultKafkaConsumerFactory<>(
                        getDefaultConsumerConfigs(STRING)));

        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> jsonKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
                new DefaultKafkaConsumerFactory<>(
                        getDefaultConsumerConfigs(JSON)));
        factory.setMessageConverter(new StringJsonMessageConverter());

        return factory;
    }

    private Map<String, Object> getDefaultConsumerConfigs(KafkaClientIdType type) {
        return Map.ofEntries(
                Map.entry(BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers()),
                Map.entry(GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId()),
                Map.entry(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class),
                Map.entry(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class),
                Map.entry(CLIENT_ID_CONFIG, kafkaProperties.getClient().getId().concat("-").concat(type.name().toLowerCase()))
        );
    }
}