package com.streaming.Kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("api/kafka")
public class ProducerController {
    Logger logger = LoggerFactory.getLogger(ProducerController.class);
    @Value("${spring.kafka.user.topic}")
    private String userTopic;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/publish")
    public String sendMessageToUserTopic(@RequestBody String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(userTopic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });

        return "Success send message";
    }
}
