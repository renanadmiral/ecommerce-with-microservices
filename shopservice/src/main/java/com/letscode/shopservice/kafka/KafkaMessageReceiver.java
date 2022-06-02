package com.letscode.shopservice.kafka;

import com.letscode.shopservice.model.Shop;
import com.letscode.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageReceiver {

    public static final String KAFKA_TOPIC = "SHOP_QUEUE";

    private final ShopService shopService;

    @KafkaListener(topics = KAFKA_TOPIC, groupId = "group-1")
    public void listenTopic(Shop shop) {

        log.info("Saving new shop...");
        shopService.saveShop(shop);
    }
}
