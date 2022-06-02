package com.letscode.shoppingcartservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private String codeNumber;

    private String name;

    private BigDecimal value;

    private Integer amount;
}
