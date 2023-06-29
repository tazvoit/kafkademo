package com.nuup.kafkademo.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "cambios-de-accion", groupId = "consumers-springboot")
    public void consumeMessage(String message) {
        System.out.println("Mensaje recibido: " + message);
        // Realiza el procesamiento necesario con el mensaje recibido
    }
}

