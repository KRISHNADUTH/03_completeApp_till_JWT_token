package com.example.EazyBankApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EazyBankApp.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    Account findByCustomerId(int id);

}
