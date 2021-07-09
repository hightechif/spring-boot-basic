package com.fadhil.basicspringboot.service;

import com.fadhil.basicspringboot.model.Customer;
import com.fadhil.basicspringboot.repository.CustomerRepo;   // Import our repo interface
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Customer> getAllCustomer() {             // Get All Customer Handler
        return customerRepo.findAll();
    }

    public Iterable<Customer> getByName(String name) {      // Get Customer by Name Handler
        return customerRepo.findCustomerByName(name);
    }
}
