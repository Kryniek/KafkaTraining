package com.training.kafka.KafkaTraining.config.properties;

import lombok.Data;

@Data
public class ConsumerProperties {
    private String groupId;
    private String autoOffsetReset;
}