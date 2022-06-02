package com.letscode.shopvalidatorservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdressDTO {

    private String street;

    private String buildingNumber;

    private String city;

    private String state;

    private String country;

    private String zipcode;
}
