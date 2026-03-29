package com.example.uber.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RideEventConsumer {


    @KafkaListener(topics = "ride-created", groupId = "ride-group")
    public void handleRideCreatedEvent(String message){
        System.out.println("Received Kafka event:" + message);
    }


}
