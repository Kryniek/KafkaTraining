package com.training.kafka.KafkaTraining.producer;

import com.training.kafka.KafkaTraining.config.kafka.properties.KafkaProperties;
import com.training.kafka.KafkaTraining.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserProducer {
    private final KafkaTemplate<String, Object> template;
    private final KafkaProperties kafkaProperties;

    public void send(User user) {
        template.send(kafkaProperties.getTopic().getUserTopic(), user);
    }
}
