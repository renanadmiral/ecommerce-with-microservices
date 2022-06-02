package com.letscode.customerservice.model;

import com.letscode.customerservice.dto.CustomerDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Calendar birthDate;

    @Column(unique = true)
    private String registrationNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Adress> adresses;

    public static Customer convertToModel(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .birthDate(customerDTO.getBirthDate())
                .registrationNumber(customerDTO.getRegistrationNumber())
                .adresses(new ArrayList<>())
                .build();
    }
}
