package com.letscode.shoppingcartservice.service;

import com.letscode.shoppingcartservice.kafka.KafkaMessageSender;
import com.letscode.shoppingcartservice.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final KafkaMessageSender kafkaMessageSender;

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {

        redisTemplate.opsForValue().set(
                shoppingCart.getCustomerRegistration(),
                shoppingCart,
                1,
                TimeUnit.DAYS
        );

        return shoppingCart;
    }

    public ShoppingCart getShoppingCart(String customerRegistration) {

        ShoppingCart shoppingCart = (ShoppingCart) redisTemplate.opsForValue().get(customerRegistration);

        if (shoppingCart == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping cart not found");

        return shoppingCart;
    }

    public ShoppingCart sendShoppingCartToValidation(String customerRegistration) {

        ShoppingCart shoppingCart = this.getShoppingCart(customerRegistration);

        return kafkaMessageSender.sendMessage(shoppingCart);
    }
}
