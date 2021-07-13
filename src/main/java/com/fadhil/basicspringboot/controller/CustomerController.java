package com.fadhil.basicspringboot.controller;

import com.fadhil.basicspringboot.dto.CustomerDTO;
import com.fadhil.basicspringboot.model.Customer;
import com.fadhil.basicspringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller are where all routing
 * and method of application.
 * */

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createCustomer(String name, String email) {
        customerService.createCustomer(name, email);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<CustomerDTO> getAll() {
        return customerService.getAllCustomer();
    }

    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET)
    public Iterable<Customer> getByName(String name) {
        return customerService.getByName(name);
    }
}
