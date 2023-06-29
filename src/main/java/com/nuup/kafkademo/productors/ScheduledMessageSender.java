package com.nuup.kafkademo.productors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class ScheduledMessageSender {

    private final KafkaProducer kafkaProducer;
    private final StockPriceChangeGenerator stockPriceChangeGenerator;

    @Autowired
    public ScheduledMessageSender(KafkaProducer kafkaProducer, StockPriceChangeGenerator stockPriceChangeGenerator) {
        this.kafkaProducer = kafkaProducer;
        this.stockPriceChangeGenerator = stockPriceChangeGenerator;
    }

    @Scheduled(fixedDelay = 1000) // Ejecutar cada 2 segundos
    public void sendScheduledMessage() {
        int messageId = 1;
        double stockPrice = kafkaProducer.generateRandomStockPrice();
        String message = stockPriceChangeGenerator.generateStockPriceChangeMessage(stockPrice);
        kafkaProducer.sendMessage(message);
        System.out.println("Mensaje programado enviado: " + message);
    }

}
