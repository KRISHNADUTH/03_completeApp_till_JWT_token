package com.example.EazyBankApp.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.EazyBankApp.model.Authority;
import com.example.EazyBankApp.model.Customer;

public class CustomerConverter {
    public static Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setCreateDt(customerDto.getCreateDt());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setPwd(customerDto.getPwd());
        customer.setRole(customerDto.getRole());
        if (customerDto.getAuthorityDtos() != null) {
            Set<Authority> authorities = customerDto.getAuthorityDtos().stream().map(AuthorityConverter::toEntity)
                    .collect(Collectors.toSet());
            authorities.forEach(authority -> authority.setCustomer(customer));
            customer.setAuthorities(authorities);
        }
        return customer;
    }

    public static CustomerDto tDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setCreateDt(customer.getCreateDt());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setPwd(customer.getPwd());
        customerDto.setRole(customer.getRole());
        Set<AuthorityDto> authorityDtos = new HashSet<>();
        if (customer.getAuthorities() != null)
        {
            authorityDtos = customer.getAuthorities().stream().map(authority -> AuthorityConverter.toDto(authority))
                    .collect(Collectors.toSet());
            customerDto.setAuthorityDtos(authorityDtos);
        }
        return customerDto;
    }
}
