package com.training.kafka.KafkaTraining.config.kafka.properties;

import lombok.Data;

@Data
public class ConsumerProperties {
    private String groupId;
    private String autoOffsetReset;
}