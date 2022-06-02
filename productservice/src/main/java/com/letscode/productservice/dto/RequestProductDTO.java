package com.letscode.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestProductDTO {

    private String name;

    private String codeNumber;

    private BigDecimal value;

    private Integer stockAmmount;
}
