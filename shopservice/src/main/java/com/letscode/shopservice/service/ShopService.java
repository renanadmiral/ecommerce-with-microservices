package com.letscode.shopservice.service;

import com.letscode.shopservice.dto.ShopDTO;
import com.letscode.shopservice.model.Shop;
import com.letscode.shopservice.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {

    private final ShopRepository shopRepository;

    public List<ShopDTO> getAllShops() {

        return ShopDTO.convertListToDTO(shopRepository.findAll());
    }

    public void saveShop (Shop shop) {

        calculateTotal(shop);
        shopRepository.insert(shop);
        log.info("Shop saved.");
    }

    private void calculateTotal(Shop shop) {

        shop.getProducts().forEach(product -> {
            shop.setTotalValue(
                    shop.getTotalValue().add(
                            product.getValue().multiply(
                                    new BigDecimal(product.getAmount())
                            )
                    )
            );
        });
    }
}
