package com.training.kafka.KafkaTraining.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("training.kafka")
@Data
public class KafkaProperties {
    private String bootstrapServers;
    private ConsumerProperties consumer;
    private TopicProperties topic;
}
