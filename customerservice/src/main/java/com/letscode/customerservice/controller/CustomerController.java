package com.letscode.customerservice.controller;

import com.letscode.customerservice.dto.AdressDTO;
import com.letscode.customerservice.dto.CustomerDTO;
import com.letscode.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO postCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return CustomerDTO.convertToDTO(
                customerService.saveCustomer(customerDTO)
        );
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{registrationNumber}")
    public CustomerDTO getCustomerByRegistration(@PathVariable String registrationNumber) {
        return CustomerDTO.convertToDTO(
                customerService.getCustomerByRegistration(registrationNumber)
        );
    }

    @PostMapping("/{registrationNumber}/adress")
    public CustomerDTO postCustomerAdress(@PathVariable String registrationNumber, @RequestBody AdressDTO adressDTO) {
        return customerService.saveCustomerAdress(registrationNumber, adressDTO);
    }
}
