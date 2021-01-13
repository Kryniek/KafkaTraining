package com.training.kafka.KafkaTraining.controller;

import com.training.kafka.KafkaTraining.producer.MessageProducer;
import com.training.kafka.KafkaTraining.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageProducer messageProducer;

    @PostMapping
    public void send(@RequestBody Message message) {
        messageProducer.send(message.getMessage());
    }
}
