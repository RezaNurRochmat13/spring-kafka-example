package com.streaming.Kafka.consumer;

import com.streaming.Kafka.producer.ProducerController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ConsumerController {
    Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Value("${spring.kafka.user.topic}")
    private String userTopic;
    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaBrokerGroupId;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "user", groupId = "myGroup")
    public void userStreamingListener(String msg) {
        logger.info("Streaming message from user topic {} : ", msg);
    }



}
