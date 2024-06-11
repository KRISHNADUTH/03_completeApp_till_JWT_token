package com.example.EazyBankApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EazyBankApp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    Customer findByEmail(String username);

}
