package ru.detection.anomaly.wsadutilities.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageSenderTargeting {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topicName;

    public void sendMessage(Object message) {
        kafkaTemplate.send(topicName, message);
    }

}
