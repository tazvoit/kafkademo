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

    private final Random random;

    @Autowired
    public ScheduledMessageSender(KafkaProducer kafkaProducer, StockPriceChangeGenerator stockPriceChangeGenerator) {
        this.kafkaProducer = kafkaProducer;
        this.stockPriceChangeGenerator = stockPriceChangeGenerator;
        this.random = new Random();
    }

    @Scheduled(fixedDelay = 1000) // Ejecutar cada 2 segundos
    public void sendScheduledMessage() {
        int messageId = 1;
        double stockPrice = kafkaProducer.generateRandomStockPrice();
        String stockType = getRandomStockType();
        String message = stockPriceChangeGenerator.generateStockPriceChangeMessage(stockPrice);
        kafkaProducer.sendMessage(message, stockType);
        System.out.println("Mensaje programado enviado: " + message + ", Tipo de Acción: " + stockType);
    }

    private String getRandomStockType() {
        String[] stockTypes = {"AAPL", "GOOGL", "MSFT", "AMZN", "FB"};
        int randomIndex = random.nextInt(stockTypes.length);
        return stockTypes[randomIndex];
    }

}
