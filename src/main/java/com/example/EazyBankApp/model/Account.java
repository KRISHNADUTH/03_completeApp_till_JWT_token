package com.example.EazyBankApp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long customerId;
    @Id
    private Integer accountNumber;
    private String accountType;
    private String branchAddress;
    private String createDt;
    
    // @JsonIgnore  - Not working as expected. Need to study more about this kind of mapping.
    // @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name="account_number", referencedColumnName = "accountNumber")
    // private List<AccountTransactions> transactions;
}
