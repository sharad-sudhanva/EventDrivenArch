package com.example.order_service.kafka;

import com.example.order_service.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    private final NewTopic topic;
    public final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendMessage(OrderEvent orderEvent){
        logger.info(String.format("Order Event : %s", orderEvent.toString()));

        kafkaTemplate.send(topic.name(),
                orderEvent.getOrderId(),
                orderEvent);
    }
}
