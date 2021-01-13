package com.training.kafka.KafkaTraining.consumer;

import com.training.kafka.KafkaTraining.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {
    private final KafkaProperties kafkaProperties;

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String message) {
        log.info("Consumed message. Topic: {}, message: {}.", kafkaProperties.getTopic().getTestTopic(), message);
    }
}
