package com.letscode.customerservice.dto;

import com.letscode.customerservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank
    private String name;

    @NotNull
    private Calendar birthDate;

    @NotBlank
    @Size(min = 11, max = 11, message = "teste")
    private String registrationNumber;

    private List<AdressDTO> adresses;

    public static CustomerDTO convertToDTO(Customer customer) {

        return CustomerDTO.builder()
                .name(customer.getName())
                .birthDate(customer.getBirthDate())
                .registrationNumber(customer.getRegistrationNumber())
                .adresses(AdressDTO.convertListToDTO(customer.getAdresses()))
                .build();
    }

    public static List<CustomerDTO> convertListToDTO(List<Customer> customers) {

        return customers.stream()
                .map(CustomerDTO::convertToDTO)
                .collect(Collectors.toList());
    }
}
