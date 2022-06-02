package com.letscode.shopvalidatorservice.service;

import com.letscode.shopvalidatorservice.dto.customer.CustomerDTO;
import com.letscode.shopvalidatorservice.model.Product;
import com.letscode.shopvalidatorservice.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

    private final CustomerService customerService;

    private final ProductService productService;

    public boolean ValidateShoppingCart (ShoppingCart shoppingCart) {

        CustomerDTO customer = customerService.getCustomerByRegistration(shoppingCart.getCustomerRegistration());

        List<Product> products = productService.getAllCartProducts(

                shoppingCart.getProducts()
                        .stream().map(Product::getCodeNumber)
                        .collect(Collectors.toList())
        );

        boolean isCustomerValid = validateCustomer(customer, shoppingCart.getCustomerRegistration());
        boolean isProductsValid = validateProducts(products, shoppingCart.getProducts());

        if (isCustomerValid && isProductsValid) {
            return true;
        }

        return false;
    }

    private boolean validateCustomer (CustomerDTO realCustomer, String registrationToBeValidated) {

        if (realCustomer.getRegistrationNumber() == null) {
            log.error("Customer not found");
            return false;
        }


        if (!registrationToBeValidated.equals(realCustomer.getRegistrationNumber())) {
            log.error("Customers registrations are different.");
            return false;
        }

        if (realCustomer.getAdresses().isEmpty()) {
            log.error("The Customer has not registered an adress.");
            return false;
        }

        return true;
    }

    private boolean validateProducts (List<Product> realProducts, List<Product> productsToBeValidated) {

        for ( Product product : realProducts) {
            if (product.getCodeNumber() == null) {
                log.error("At least 1 product was not found.");
                return false;
            }
        }

        for ( Product product : productsToBeValidated) {
            if (product.getAmount() < 1) {
                log.error("A product amount to be bought must be bigger than 1.");
                return false;
            }
        }

        return true;
    }
}
