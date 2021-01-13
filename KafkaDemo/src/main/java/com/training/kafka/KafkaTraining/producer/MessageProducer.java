package com.training.kafka.KafkaTraining.producer;

import com.training.kafka.KafkaTraining.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaTemplate<String, String> template;
    private final KafkaProperties kafkaProperties;

    public void send(String message) {
        String topic = kafkaProperties.getTopic().getTestTopic();
        ListenableFuture<SendResult<String, String>> sendResultFuture = template.send(topic, message);
        sendResultFuture.addCallback(
                successCallback -> log.info("Sent message. Topic: {}, message: {}.", topic, message),
                failureCallback -> log.info("Failed to sent message. Topic: {}, message: {}.", topic, message));
    }
}
