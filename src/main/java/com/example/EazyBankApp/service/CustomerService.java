package com.example.EazyBankApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.EazyBankApp.dto.AuthorityDto;
import com.example.EazyBankApp.dto.CustomerDto;
import com.example.EazyBankApp.model.Authority;
import com.example.EazyBankApp.model.Customer;
import com.example.EazyBankApp.repo.CustomerRepository;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository cuswtomerRepository) {
        this.customerRepository = cuswtomerRepository;
    }
    
    public ResponseEntity<String> submitCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        customer.setPwd(customerDto.getPwd());
        customer.setRole(customerDto.getRole());

        for (AuthorityDto authorityDto : customerDto.getAuthorityDtos()) {
            System.out.println("Error is happening here......................................................");
            Authority authority = new Authority(authorityDto.getName());
            customer.addAuthority(authority);
        }
        
        // return new ResponseEntity<String>("There will be error When I try to save this entity..................................................................", HttpStatus.ACCEPTED);
        return new ResponseEntity<String>(customerRepository.save(customer).toString(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Customer> getCustomer(int id) {
        return new ResponseEntity<Customer>(customerRepository.findById(id).get(), HttpStatus.OK);
    }

}
