package com.maryafolabi.bankapp.service.serviceimplementation;

import com.maryafolabi.bankapp.model.Account;
import com.maryafolabi.bankapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
//import static org.aspectj.core.api.Assertions.assertThat;

@SpringBootTest
@AllArgsConstructor
class AccountServiceImplementationTest {

    private final List<Account> accounts;

    private final AccountService accountService;




    @Test
    void findByAccountNumberAndPassword() {
        Account account = new Account(1l, "mary", "1223400921", "password", 100000d);

        Account expectedAccount = (Account) accountService.findByAccountNumberAndPassword(account.getAccountNumber()).getBody();

        given((Account) accountService.findByAccountNumberAndPassword(account.getAccountNumber()).getBody()).willReturn(account);


//        assertEquals((Account) accountService.findByAccountNumberAndPassword(account.getAccountNumber()).getBody(),
//                account);
        when((Account) accountService.findByAccountNumberAndPassword(account.getAccountNumber()).getBody()).thenReturn(account);
    }

    @Test
    void getTransaction() {
    }

    @Test
    void depositMoney() {
    }

    @Test
    void withdrawMoney() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void findByAccountNumber() {
    }

    @Test
    void accountLogin() {
    }
}