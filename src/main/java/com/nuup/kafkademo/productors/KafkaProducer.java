package com.nuup.kafkademo.productors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Random random;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.random = new Random();
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("cambios-de-accion", message);
        System.out.println("Mensaje enviado: " + message);
    }

    public double generateRandomStockPrice() {
        // Generar un precio de acción aleatorio entre 10 y 100
        return 10 + random.nextDouble() * (100 - 10);
    }
}