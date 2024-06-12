package com.example.EazyBankApp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.EazyBankApp.model.Authority;
import com.example.EazyBankApp.model.Customer;
import com.example.EazyBankApp.repo.AuthorityRepository;
import com.example.EazyBankApp.repo.CustomerRepository;

@Component
public class EazyBankUserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    CustomerRepository customerRepository;
    PasswordEncoder passwordEncoder;
    AuthorityRepository authorityRepository;
    public EazyBankUserNamePasswordAuthenticationProvider(CustomerRepository customerRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }


    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(username);
        System.out.println("Reaching in customw auth provider .....................................................................................................");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (passwordEncoder.matches(password, customer.getPwd())) {
            System.out.println("Password matched in custom auth provider...........................................................................................................");
            List<Authority> authorities = authorityRepository.findByCustomerId(customer.getId());
            authorities.forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName())));
            grantedAuthorities.add(new SimpleGrantedAuthority(customer.getRole()));  // Fix for "CSrfToken not working" commited on 11/06/2024. Previously we were adding authorities from Authority table only missed to add the authority(role) from role column of Customer table.
            System.out.println("UsernamePasswordAuthenticationToken is - "+new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities)+"......................................................................");
            return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        } else {
            throw new BadCredentialsException("Wrong password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
