package com.nuup.kafkademo.productors;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class KafkaProducer {


    private static final String TOPIC_NAME = "cambios-de-accion"; // Reemplaza con el nombre de tu topic
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Random random;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.random = new Random();
    }

    public void sendMessage(String message,String stockType) {
        kafkaTemplate.send(TOPIC_NAME, stockType, message);
        System.out.println("Mensaje enviado: " + message);
    }

    public double generateRandomStockPrice() {
        // Generar un precio de acción aleatorio entre 10 y 100
        return 10 + random.nextDouble() * (100 - 10);
    }

    public static class StockPartitioner implements org.apache.kafka.clients.producer.Partitioner {
        @Override
        public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, org.apache.kafka.common.Cluster cluster) {
            java.util.List<org.apache.kafka.common.PartitionInfo> partitions = cluster.partitionsForTopic(topic);
            int numPartitions = partitions.size();

            // Asignar una partición basada en el tipo de acción (key)
            int partition = Math.abs(key.hashCode() % numPartitions);

            return partition;
        }

        @Override
        public void close() {
        }

        @Override
        public void configure(Map<String, ?> configs) {
        }
    }
}