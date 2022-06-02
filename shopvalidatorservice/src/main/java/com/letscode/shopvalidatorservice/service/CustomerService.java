package com.letscode.shopvalidatorservice.service;

import com.letscode.shopvalidatorservice.dto.customer.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final WebClient customerWebClient = WebClient.create("http://localhost:8081");

    public CustomerDTO getCustomerByRegistration (String registrationNumber) {

        return customerWebClient.get()
                .uri("/customer/{registrationNumber}", registrationNumber)
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Mono.just(CustomerDTO.builder().build()) : Mono.error(ex))
                .block();
    }
}
