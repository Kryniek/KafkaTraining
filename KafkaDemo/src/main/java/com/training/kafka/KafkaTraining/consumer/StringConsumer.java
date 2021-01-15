package com.training.kafka.KafkaTraining.consumer;

import com.training.kafka.KafkaTraining.util.aop.KafkaConsumerLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringConsumer {
    private Integer consumedMessages = 0;

    @KafkaConsumerLog
    @KafkaListener(topics = "${training.kafka.topic.string-topic}",
            groupId = "${training.kafka.consumer.group-id}")
    public void listen(String message) {
        consumedMessages++;
        log.info("Consumed messages: {}", consumedMessages);
    }
}
