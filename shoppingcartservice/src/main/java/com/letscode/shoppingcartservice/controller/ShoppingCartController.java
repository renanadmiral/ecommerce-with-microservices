package com.letscode.shoppingcartservice.controller;

import com.letscode.shoppingcartservice.model.ShoppingCart;
import com.letscode.shoppingcartservice.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCart postShoppingCart(@RequestBody ShoppingCart shoppingCart) {

        return shoppingCartService.saveShoppingCart(shoppingCart);
    }

    @GetMapping("/{customerRegistration}")
    public ShoppingCart getShoppingCartByRegistration(@PathVariable String customerRegistration) {
        return shoppingCartService.getShoppingCart(customerRegistration);
    }

    @PostMapping("/{customerRegistration}/validate")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCart postShoppingCartToValidation (@PathVariable String customerRegistration) {

        return shoppingCartService.sendShoppingCartToValidation(customerRegistration);
    }
}
