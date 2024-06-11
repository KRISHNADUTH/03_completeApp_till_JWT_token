package com.example.EazyBankApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.EazyBankApp.model.Account;
import com.example.EazyBankApp.service.AccountService;

@RestController
public class AccountController {
    
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    AccountService accountService;
    
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountDetailsById(@PathVariable int id) {
        return accountService.getAccountDetailsById(id);
    }

    @PostMapping("/account")
    public ResponseEntity<String> submitAccountDetails(@RequestBody Account account)
    {
        return accountService.submitAccountDetails(account);
    }

    @PutMapping("/account")
    public ResponseEntity<String> updateAccountDetails(@RequestBody Account newAccountDetails)
    {
        return accountService.updateAccountDetails(newAccountDetails);
    }

    @DeleteMapping("/account")
    public ResponseEntity<String> deleteAcocuntDetails(@RequestBody Account account)
    {
        return accountService.deleteAcocuntDetails(account);
    }
}
