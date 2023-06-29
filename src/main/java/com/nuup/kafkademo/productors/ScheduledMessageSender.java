package com.nuup.kafkademo.productors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class ScheduledMessageSender {

    private final KafkaProducer kafkaProducer;
    private final StockPriceChangeGenerator stockPriceChangeGenerator;
    private final Random random;

    @Autowired
    public ScheduledMessageSender(KafkaProducer kafkaProducer, StockPriceChangeGenerator stockPriceChangeGenerator, Random random) {
        this.kafkaProducer = kafkaProducer;
        this.stockPriceChangeGenerator = stockPriceChangeGenerator;
        this.random = random;
    }

    @Scheduled(fixedDelay = 1000) // Ejecutar cada 2 segundos
    public void sendScheduledMessage() {
        int messageId = 1;
        double stockPrice = generateRandomStockPrice();
        String message = stockPriceChangeGenerator.generateStockPriceChangeMessage(messageId++, stockPrice);
        kafkaProducer.sendMessage(message);
        System.out.println("Mensaje programado enviado: " + message);
    }

    private double generateRandomStockPrice() {
        // Generar un precio de acción aleatorio entre 10 y 100
        return 10 + random.nextDouble() * (100 - 10);
    }@Scheduled(fixedDelay = 1000) // Ejecutar cada 2 segundos
    public void sendScheduledMessage() {
        int messageId = 1;
        double stockPrice = generateRandomStockPrice();
        String message = stockPriceChangeGenerator.generateStockPriceChangeMessage(messageId++, stockPrice);
        kafkaProducer.sendMessage(message);
        System.out.println("Mensaje programado enviado: " + message);
    }

}
