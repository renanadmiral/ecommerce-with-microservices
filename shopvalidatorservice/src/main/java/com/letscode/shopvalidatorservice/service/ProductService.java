package com.letscode.shopvalidatorservice.service;

import com.letscode.shopvalidatorservice.dto.customer.CustomerDTO;
import com.letscode.shopvalidatorservice.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {

    private final WebClient productWebClient = WebClient.create("http://localhost:8082");

    private Mono<Product> getAProductByCodeNumber (String codeNumber) {

        return productWebClient.get()
                .uri("/product/{codeNumber}", codeNumber)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Mono.just(Product.builder().build()) : Mono.error(ex));

    }

    public List<Product> getAllCartProducts (List<String> codeNumbers) {

        return Flux.fromIterable(codeNumbers)
                .flatMap(this::getAProductByCodeNumber)
                .collectList().block();
    }
}
