package com.letscode.shopvalidatorservice.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String codeNumber;

    private String name;

    private BigDecimal value;

    private Integer amount;
}
