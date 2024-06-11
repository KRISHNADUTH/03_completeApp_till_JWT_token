package com.example.EazyBankApp.service;

import java.sql.Date;
import java.time.LocalDate;

// import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.EazyBankApp.model.Account;
import com.example.EazyBankApp.repo.AccountRepository;

@Service
public class AccountService {
    
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<String> submitAccountDetails(Account account) {
        account.setCreateDt(String .valueOf(new Date(System.currentTimeMillis())));
        return new ResponseEntity<String>(accountRepository.save(account).toString(), HttpStatus.OK);
    }

    AccountRepository accountRepository;
    
    public ResponseEntity<Account> getAccountDetailsById(int id) {
            Account account = accountRepository.findByCustomerId(id);
            if(account != null)
            {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            else 
            {
                System.out.println("Requested account details not present in db.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    public ResponseEntity<String> updateAccountDetails(Account newAccountDetails) {
        if (accountRepository.existsById(newAccountDetails.getAccountNumber())) {
            return new ResponseEntity<>(accountRepository.save(newAccountDetails).toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Account detail to be updated could not found in DB ",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteAcocuntDetails(Account account) {
        if (accountRepository.existsById(account.getAccountNumber())) {
            accountRepository.delete(account);
            return new ResponseEntity<String>("Account detail deleted successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
