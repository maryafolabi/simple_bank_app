package com.maryafolabi.bankapp.controller;


import com.maryafolabi.bankapp.payload.request.DepositRequest;
import com.maryafolabi.bankapp.payload.request.WithdrawalRequest;
import com.maryafolabi.bankapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AccountController {


   private final AccountService accountService;

    @GetMapping("/account_info/{accountNumber}")
    public ResponseEntity<?> getAccountBalance(@PathVariable String accountNumber) {
        return accountService.findByAccountNumberAndPassword(accountNumber);
    }

    @GetMapping("/account_statement/{accountNumber}")
    public ResponseEntity<?> getAccountStatement(@PathVariable String accountNumber) {
        return accountService.getTransaction(accountNumber);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> makeDeposit(@RequestBody DepositRequest depositRequest) {
        return accountService.depositMoney(depositRequest);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<?> makeWithdrawal(@RequestBody WithdrawalRequest withdrawalRequest) {
        return accountService.withdrawMoney(withdrawalRequest);
    }
}