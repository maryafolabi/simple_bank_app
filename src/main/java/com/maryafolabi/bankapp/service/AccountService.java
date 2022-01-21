package com.maryafolabi.bankapp.service;

import com.maryafolabi.bankapp.model.Account;
import com.maryafolabi.bankapp.payload.request.DepositRequest;
import com.maryafolabi.bankapp.payload.request.LoginRequest;
import com.maryafolabi.bankapp.payload.request.NewAccountRequest;
import com.maryafolabi.bankapp.payload.request.WithdrawalRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    ResponseEntity<?> findByAccountNumberAndPassword(String accountNumber);
    ResponseEntity<?> getTransaction(String accountNumber);
    ResponseEntity<?> depositMoney(DepositRequest depositRequest);
    ResponseEntity<?> withdrawMoney(WithdrawalRequest withdrawalRequest);
    ResponseEntity<?> createAccount(NewAccountRequest newAccountRequest);
    Account findByAccountNumber(String accountNumber);
    ResponseEntity<?> accountLogin(LoginRequest loginRequest);
}
