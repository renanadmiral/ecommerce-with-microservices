package com.letscode.productservice.dto;

import com.letscode.productservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO {

    private String name;

    private String codeNumber;

    private BigDecimal value;

    public static ResponseProductDTO convertToDTO(Product product) {

        return ResponseProductDTO.builder()
                .name(product.getName())
                .codeNumber(product.getCodeNumber())
                .value(product.getValue())
                .build();
    }
}
