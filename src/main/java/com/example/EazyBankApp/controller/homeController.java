package com.example.EazyBankApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    @GetMapping("/")
    public ResponseEntity<String> gethome() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
