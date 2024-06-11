package com.example.EazyBankApp.dto;

import com.example.EazyBankApp.model.Authority;
import com.example.EazyBankApp.model.Customer;

public class AuthorityConverter {

    public static Authority toEntity(AuthorityDto authorityDto) {
        Authority authority = new Authority();
        authority.setId(authorityDto.getId());
        authority.setName(authority.getName());
        Customer customer = new Customer();
        customer.setId(authorityDto.getCustomerId());
        authority.setCustomer(customer);
        return authority;
    }

    public static AuthorityDto toDto(Authority authority) {
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setCustomerId(authority.getCustomer().getId()); // Avoid circular reference
        authorityDto.setName(authority.getName());

        return authorityDto;
    }
    
}
