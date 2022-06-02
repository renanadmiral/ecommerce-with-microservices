package com.letscode.shopservice.dto;

import com.letscode.shopservice.model.Product;
import com.letscode.shopservice.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    private String customerRegistration;

    private BigDecimal totalValue;

    private List<Product> products;

    public static ShopDTO convertToDTO (Shop shop) {

        return ShopDTO.builder()
                .customerRegistration(shop.getCustomerRegistration())
                .totalValue(shop.getTotalValue())
                .products(shop.getProducts())
                .build();
    }

    public static List<ShopDTO> convertListToDTO (List<Shop> shops) {

        return shops.stream()
                .map(ShopDTO::convertToDTO)
                .collect(Collectors.toList());
    }
}
