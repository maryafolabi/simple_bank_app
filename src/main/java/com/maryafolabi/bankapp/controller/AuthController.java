package com.maryafolabi.bankapp.controller;

import com.maryafolabi.bankapp.payload.request.LoginRequest;
import com.maryafolabi.bankapp.payload.request.NewAccountRequest;
import com.maryafolabi.bankapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
public class AuthController {

    private final AccountService accountService;


    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody NewAccountRequest newAccountRequest){
        return accountService.createAccount(newAccountRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        return accountService.accountLogin(loginRequest);
    }

}
