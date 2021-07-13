package com.fadhil.basicspringboot.service;

import com.fadhil.basicspringboot.dto.CustomerDTO;
import com.fadhil.basicspringboot.model.Customer;
import com.fadhil.basicspringboot.repository.CustomerRepo;   // Import our repo interface
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service is where all of
 * business logics was created
 * */

@Service     // We persist CustomerService to Service Decorator
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void createCustomer(String name, String email){   // Create Customer Handler
        customerRepo.save(new Customer(name, email));
    }

    public List<CustomerDTO> getAllCustomer() {             // Get All Customer Handler
        List<Customer> customer = customerRepo.findAll();
        return customer.stream().map(x -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(x.getId());
            customerDTO.setName(x.getName());
            customerDTO.setEmail(x.getEmail());
            return customerDTO;
        }).collect(Collectors.toList());
    }

    public Iterable<Customer> getByName(String name) {      // Get Customer by Name Handler
        return customerRepo.findCustomerByName(name);
    }
}
