package com.training.kafka.KafkaTraining.consumer;

import com.training.kafka.KafkaTraining.model.User;
import com.training.kafka.KafkaTraining.util.aop.KafkaConsumerLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserConsumer {
    private Integer consumedMessages = 0;

    @KafkaConsumerLog
    @KafkaListener(topics = "${training.kafka.topic.user-topic}",
            groupId = "${training.kafka.consumer.group-id}",
            containerFactory = "jsonKafkaListenerContainerFactory")
    public void listen(User user) {
        consumedMessages++;
        log.info("Consumed messages: {}", consumedMessages);
    }
}
