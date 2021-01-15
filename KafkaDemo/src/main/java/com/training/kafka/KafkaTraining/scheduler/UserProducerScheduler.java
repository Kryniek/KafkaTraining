package com.training.kafka.KafkaTraining.scheduler;

import com.training.kafka.KafkaTraining.model.User;
import com.training.kafka.KafkaTraining.producer.UserProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@ConditionalOnProperty(value = "training.scheduler.enable-user-producer-tester", havingValue = "true")
@Component
@RequiredArgsConstructor
public class UserProducerScheduler {
    private final UserProducer userProducer;

    @Scheduled(fixedDelay = 30000)
    public void produceMessage() {
        final int MAX_MESSAGES_NUMBER = 10000;
        int messagesNumber = 0;

        log.info("[MESSAGE_PRODUCER_TESTER] Started to test user producer...");

        do {
            userProducer.send(User.builder()
                    .id("345")
                    .field1("field 1 test")
                    .field2("field 2 test")
                    .field3("field 3 test")
                    .build());
            messagesNumber++;
        } while (messagesNumber < MAX_MESSAGES_NUMBER);

        log.info("[MESSAGE_PRODUCER_TESTER] Finished testing user producer!");
    }
}
