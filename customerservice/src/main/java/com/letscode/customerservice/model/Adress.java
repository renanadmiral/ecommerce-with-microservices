package com.letscode.customerservice.model;

import com.letscode.customerservice.dto.AdressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String street;

    @Column
    private String buildingNumber;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    public static Adress convertToModel(AdressDTO adressDTO, Customer customer) {

        return Adress.builder()
                .street(adressDTO.getStreet())
                .buildingNumber(adressDTO.getBuildingNumber())
                .city(adressDTO.getCity())
                .state(adressDTO.getState())
                .country(adressDTO.getCountry())
                .zipcode(adressDTO.getZipcode())
                .customer(customer)
                .build();
    }
}
