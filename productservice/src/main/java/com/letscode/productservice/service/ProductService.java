package com.letscode.productservice.service;

import com.letscode.productservice.dto.RequestProductDTO;
import com.letscode.productservice.model.Product;
import com.letscode.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Mono<Product> saveProduct(RequestProductDTO productDTO) {

        Product product = Product.convertToModel(productDTO);

        return productRepository.insert(product);
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> getProductByCodeNumber(String codeNumber) {

        return productRepository
                .findByCodeNumber(codeNumber)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
                ));
    }
}
