package com.example.EazyBankApp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.EazyBankApp.model.Customer;
import com.example.EazyBankApp.repo.CustomerRepository;

@Service
public class EazyBankUserDetailService implements UserDetailsService {

    CustomerRepository customerRepository;
    

    public EazyBankUserDetailService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Customer customer = customerRepository.findByEmail(username);
        String userName;
        String passWord;
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(customer != null)
        {
            userName = customer.getEmail();
            passWord = customer.getPwd();
            authorities.add(new SimpleGrantedAuthority(customer.getRole()));
            System.out.println("User details Loaded........................................");
        }
        else
        {
            throw new UsernameNotFoundException("User with the given email not found");
        }
        return new User(userName, passWord, authorities);
    }
    
}
