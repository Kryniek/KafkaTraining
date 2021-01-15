package com.training.kafka.KafkaTraining.controller;

import com.training.kafka.KafkaTraining.producer.StringProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/strings")
@RequiredArgsConstructor
public class StringController {
    private final StringProducer stringProducer;

    @PostMapping
    public void send(@RequestBody String message) {
        stringProducer.send(message);
    }
}
