package com.example.EazyBankApp.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
// This DTO object is used for data transfer purposes.
@Data
public class CustomerDto {
    private int id;

    private String name;

    private String email;

    private String mobileNumber;

    private String pwd;

    private String role;

    private String createDt;

    private Set<AuthorityDto> authorityDtos= new HashSet<>();

}
