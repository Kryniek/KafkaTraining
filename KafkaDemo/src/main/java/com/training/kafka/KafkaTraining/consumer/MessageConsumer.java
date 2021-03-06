package com.training.kafka.KafkaTraining.consumer;

import com.training.kafka.KafkaTraining.model.Message;
import com.training.kafka.KafkaTraining.util.aop.KafkaConsumerLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {
    private Integer consumedMessages = 0;

    @KafkaConsumerLog
    @KafkaListener(topics = "${training.kafka.topic.message-topic}",
            groupId = "${training.kafka.consumer.group-id}",
            containerFactory = "jsonKafkaListenerContainerFactory")
    public void listen(Message message) {
        consumedMessages++;
        log.info("Consumed messages: {}", consumedMessages);
    }
}
