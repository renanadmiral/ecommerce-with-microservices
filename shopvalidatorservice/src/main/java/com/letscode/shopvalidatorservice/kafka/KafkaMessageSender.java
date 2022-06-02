package com.letscode.shopvalidatorservice.kafka;

import com.letscode.shopvalidatorservice.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageSender {

    private final KafkaTemplate<String, ShoppingCart> kafkaTemplate;

    public static final String KAFKA_TOPIC = "SHOP_QUEUE";

    public void sendMessage(ShoppingCart shoppingCart) {
        kafkaTemplate.send(KAFKA_TOPIC, shoppingCart);
    }
}
