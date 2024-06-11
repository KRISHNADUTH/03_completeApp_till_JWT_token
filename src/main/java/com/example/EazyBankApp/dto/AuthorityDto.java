package com.example.EazyBankApp.dto;

import lombok.Data;
// This DTO object is used for data transfer purposes.
@Data
public class AuthorityDto {
    private Long id;

    private String name;

    private int customerId;
}
