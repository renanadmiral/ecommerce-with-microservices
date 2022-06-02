package com.letscode.customerservice.dto;

import com.letscode.customerservice.model.Adress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdressDTO {

    private String street;

    private String buildingNumber;

    private String city;

    private String state;

    private String country;

    private String zipcode;

    public static AdressDTO convertToDTO(Adress adress) {

        return AdressDTO.builder()
                .street(adress.getStreet())
                .buildingNumber(adress.getBuildingNumber())
                .city(adress.getCity())
                .state(adress.getState())
                .country(adress.getCountry())
                .zipcode(adress.getZipcode())
                .build();
    }

    public static List<AdressDTO> convertListToDTO(List<Adress> adresses) {

        return adresses.stream()
                .map(AdressDTO::convertToDTO)
                .collect(Collectors.toList());
    }
}
