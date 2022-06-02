package com.letscode.shopvalidatorservice.kafka;

import com.letscode.shopvalidatorservice.model.ShoppingCart;
import com.letscode.shopvalidatorservice.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageReceiver {

    public static final String KAFKA_TOPIC = "VALIDATION_QUEUE";

    private final ValidationService validationService;

    private final KafkaMessageSender kafkaMessageSender;

    @KafkaListener(topics = KAFKA_TOPIC, groupId = "group-1")
    public void listenTopic(ShoppingCart shopDTO) {

       boolean isShoppingCartValid = validationService.ValidateShoppingCart(shopDTO);

       if (isShoppingCartValid) {
           log.info("Validation done. Sending shop to be registered...");
           kafkaMessageSender.sendMessage(shopDTO);
       } else  {
           log.error("Validation failed. The shop couldn't be registered.");
       }
    }

}
