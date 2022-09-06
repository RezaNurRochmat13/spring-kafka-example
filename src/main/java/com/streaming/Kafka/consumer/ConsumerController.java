package com.streaming.Kafka.consumer;

import com.streaming.Kafka.producer.ProducerController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class ConsumerController {
    Logger logger = LoggerFactory.getLogger(ProducerController.class);
    @Value("${spring.kafka.user.topic}")
    private String userTopic;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


}
