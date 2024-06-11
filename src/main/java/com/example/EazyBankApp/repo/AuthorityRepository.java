package com.example.EazyBankApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EazyBankApp.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

    List<Authority> findByCustomerId(int id);

}
