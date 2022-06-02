package com.letscode.customerservice.service;

import com.letscode.customerservice.dto.AdressDTO;
import com.letscode.customerservice.dto.CustomerDTO;
import com.letscode.customerservice.model.Adress;
import com.letscode.customerservice.model.Customer;
import com.letscode.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer saveCustomer(CustomerDTO customerDTO) {

        Customer customer = Customer.convertToModel(customerDTO);

        return customerRepository.save(customer);
    }

    public List<CustomerDTO> getAllCustomers() {

        return CustomerDTO.convertListToDTO(customerRepository.findAll());
    }

    public Customer getCustomerByRegistration(String registration) {

           return customerRepository.findByRegistrationNumber(registration)
                    .orElseThrow(() -> {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
                    });
    }

    public CustomerDTO saveCustomerAdress(String registrationNumber, AdressDTO adressDTO) {

        Customer customer = getCustomerByRegistration(registrationNumber);

        Adress adress = Adress.convertToModel(adressDTO, customer);

        customer.getAdresses().add(adress);
        customer = customerRepository.save(customer);

        return CustomerDTO.convertToDTO(customer);
    }
}
