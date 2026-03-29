package com.example.uber.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RideEventProducer {
    private static final String TOPIC = "ride-created";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public RideEventProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRideCreatedEvent(String message){
        kafkaTemplate.send(TOPIC, message);
    }

}
