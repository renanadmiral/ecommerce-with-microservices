package com.letscode.productservice.controller;

import com.letscode.productservice.dto.RequestProductDTO;
import com.letscode.productservice.dto.ResponseProductDTO;
import com.letscode.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseProductDTO> postProduct(@RequestBody RequestProductDTO productDTO) {

        return productService.saveProduct(productDTO)
                .map(ResponseProductDTO::convertToDTO);
    }

    @GetMapping
    public Flux<ResponseProductDTO> getAllProducts() {

        return productService.getAllProducts()
                .map(ResponseProductDTO::convertToDTO);
    }

    @GetMapping("/{codeNumber}")
    public Mono<ResponseProductDTO> getProductById(@PathVariable String codeNumber) {

        return productService.getProductByCodeNumber(codeNumber)
                .map(ResponseProductDTO::convertToDTO);
    }
}
