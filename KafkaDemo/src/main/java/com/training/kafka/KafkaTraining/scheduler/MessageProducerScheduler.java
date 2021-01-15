package com.training.kafka.KafkaTraining.scheduler;

import com.training.kafka.KafkaTraining.model.Message;
import com.training.kafka.KafkaTraining.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@ConditionalOnProperty(value = "training.scheduler.enable-message-producer-tester", havingValue = "true")
@Component
@RequiredArgsConstructor
public class MessageProducerScheduler {
    private final MessageProducer messageProducer;

    @Scheduled(fixedDelay = 30000)
    public void produceMessage() {
        final int MAX_MESSAGES_NUMBER = 10000;
        int messagesNumber = 0;

        log.info("[MESSAGE_PRODUCER_TESTER] Started to test message producer...");

        do {
            messageProducer.send(Message.builder()
                    .id("123")
                    .field1("field 1 test")
                    .field2("field 2 test")
                    .build());
            messagesNumber++;
        } while (messagesNumber < MAX_MESSAGES_NUMBER);

        log.info("[MESSAGE_PRODUCER_TESTER] Finished testing message producer!");
    }
}
