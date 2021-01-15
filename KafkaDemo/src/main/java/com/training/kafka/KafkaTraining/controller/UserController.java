package com.training.kafka.KafkaTraining.controller;

import com.training.kafka.KafkaTraining.model.User;
import com.training.kafka.KafkaTraining.producer.UserProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserProducer userProducer;

    @PostMapping
    public void send(@RequestBody User user) {
        userProducer.send(user);
    }
}
