package com.training.kafka.KafkaTraining.model;

import lombok.Value;

@Value
public class Message {
    String message;
    String topic;
}
