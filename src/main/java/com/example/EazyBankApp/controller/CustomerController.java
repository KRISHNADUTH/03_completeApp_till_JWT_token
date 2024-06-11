package com.example.EazyBankApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.EazyBankApp.dto.CustomerDto;
import com.example.EazyBankApp.service.CustomerService;

@RestController
public class CustomerController {
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<String> submitCustomer(@RequestBody CustomerDto customerDto)
    {
        System.out.println("Inside POST customer request...................");
        return customerService.submitCustomer(customerDto);
    }
    
    @GetMapping("/customer/{id}")
    public ResponseEntity<String> getCustomer(@PathVariable int id) {
        return new ResponseEntity<>("GET Customer request..................", HttpStatus.OK);
        // return customerService.getCustomer(id);
    }
}
