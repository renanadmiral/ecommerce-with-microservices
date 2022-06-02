package com.letscode.productservice.model;

import com.letscode.productservice.dto.RequestProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "product")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String codeNumber;

    private BigDecimal value;

    private Integer stockAmmount;

    public static Product convertToModel(RequestProductDTO requestProductDTO) {

        return Product.builder()
                .codeNumber(requestProductDTO.getCodeNumber())
                .name(requestProductDTO.getName())
                .stockAmmount(requestProductDTO.getStockAmmount())
                .value(requestProductDTO.getValue())
                .build();
    }
}
