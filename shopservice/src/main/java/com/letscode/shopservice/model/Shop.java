package com.letscode.shopservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "shop")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    private String id;

    @Field
    private String customerRegistration;

    @Field
    @Setter
    private BigDecimal totalValue = new BigDecimal(0);

    @Field
    private List<Product> products;
}
