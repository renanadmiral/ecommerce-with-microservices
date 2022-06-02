package com.letscode.shopvalidatorservice.dto.customer;

import lombok.*;

import java.util.Calendar;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerDTO {

    private String name;

    private Calendar birthDate;

    private String registrationNumber;

    private List<AdressDTO> adresses;
}
