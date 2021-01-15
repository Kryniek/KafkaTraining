package com.training.kafka.KafkaTraining.scheduler;

import com.training.kafka.KafkaTraining.producer.StringProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@ConditionalOnProperty(value = "training.scheduler.enable-string-producer-tester", havingValue = "true")
@Component
@RequiredArgsConstructor
public class StringProducerScheduler {
    private final StringProducer stringProducer;

    @Scheduled(fixedDelay = 30000)
    public void produceMessage() {
        final int MAX_MESSAGES_NUMBER = 10000;
        int messagesNumber = 0;

        log.info("[STRING_PRODUCER_TESTER] Started to test string producer...");

        do {
            stringProducer.send("Test message");
            messagesNumber++;
        } while (messagesNumber < MAX_MESSAGES_NUMBER);

        log.info("[STRING_PRODUCER_TESTER] Finished testing string producer!");
    }
}

