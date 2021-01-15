package com.training.kafka.KafkaTraining.producer;

import com.training.kafka.KafkaTraining.config.kafka.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StringProducer {
    private final KafkaTemplate<String, String> template;
    private final KafkaProperties kafkaProperties;

    public void send(String message) {
        template.send(kafkaProperties.getTopic().getStringTopic(), message);
    }
}
