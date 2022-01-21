package com.maryafolabi.bankapp.service.serviceimplementation;

import com.maryafolabi.bankapp.model.Account;
import com.maryafolabi.bankapp.model.Transaction;
import com.maryafolabi.bankapp.model.TransactionType;
import com.maryafolabi.bankapp.payload.request.DepositRequest;
import com.maryafolabi.bankapp.payload.request.LoginRequest;
import com.maryafolabi.bankapp.payload.request.NewAccountRequest;
import com.maryafolabi.bankapp.payload.request.WithdrawalRequest;
import com.maryafolabi.bankapp.payload.response.AccountBalanceResponse;
import com.maryafolabi.bankapp.payload.response.JwtResponse;
import com.maryafolabi.bankapp.payload.response.TransactionResponse;
import com.maryafolabi.bankapp.security.jwt.JwtUtils;
import com.maryafolabi.bankapp.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImplementation implements AccountService {

    List<Account> accounts;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> findByAccountNumberAndPassword(String accountNumber) {
       for(Account account: accounts){
           if(account.getAccountNumber().equals(accountNumber)){
               AccountBalanceResponse success = new AccountBalanceResponse(200, true, "request successful", account);
               return new ResponseEntity<>(success, HttpStatus.OK);
           }
       }

        AccountBalanceResponse failed = new AccountBalanceResponse(400, false, "request unsuccessful");
        return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getTransaction(String accountNumber){

        Account myAccount = checkAccount(accounts, accountNumber);
        if(myAccount==null){
            AccountBalanceResponse failed = new AccountBalanceResponse(400, false, "invalid account Number, " + accountNumber);
            return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
        }

        double amount = 50000d;

        myAccount.setBalance(myAccount.getBalance()-amount);

        Transaction transaction = new Transaction(
                new java.util.Date(),
            TransactionType.DEPOSIT,
            "deposit of 50000",
            amount, myAccount
        );

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> depositMoney(DepositRequest depositRequest) {
        Account myAccount = checkAccount(accounts, depositRequest.getAccountNumber());

        if(myAccount==null){
            AccountBalanceResponse failed = new AccountBalanceResponse(400, false, "invalid account Number, " + depositRequest.getAccountNumber());
            return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
        }

        if(depositRequest.getAmount() > 1 && depositRequest.getAmount() < 1000000){
            myAccount.setBalance(myAccount.getBalance() + depositRequest.getAmount());
        }else {
            AccountBalanceResponse failed = new AccountBalanceResponse(400, false, "Your amount cannot be processed");
            return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
        }

        TransactionResponse transactionResponse = new TransactionResponse(200, true, "payment successful.");
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> withdrawMoney(WithdrawalRequest withdrawalRequest){
        Account myAccount = checkAccount(accounts, withdrawalRequest.getAccountNumber());

        if(myAccount==null){
            AccountBalanceResponse failed = new AccountBalanceResponse(400, false, "invalid account Number, " + withdrawalRequest.getAccountNumber());
            return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
        }

        double withdrawalBalance = myAccount.getBalance() - withdrawalRequest.getWithdrawnAmount();

        if(withdrawalRequest.getWithdrawnAmount() < 1 || withdrawalBalance < 500){
            AccountBalanceResponse failed = new AccountBalanceResponse(400, false, "insufficient funds");
            return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
        }

        myAccount.setBalance(withdrawalBalance);
        return new ResponseEntity<>(new TransactionResponse(200, true, "withdrawal successful."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createAccount(NewAccountRequest newAccountRequest) {
        long accountNumber = Math.round(Math.random() * 10000000000L);
        Long id = Math.round(Math.random() * 10);

        for(Account account : accounts) {
            if(account.getAccountName().equals(newAccountRequest.getAccountName())) {
                TransactionResponse failed = new TransactionResponse(400,false,"account name already exists.");
                return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
            }
        }

        if(newAccountRequest.getInitialDeposit() < 500){
            TransactionResponse failed = new TransactionResponse(400,false,"initial deposit below 500.");
            return new ResponseEntity<>(failed, HttpStatus.BAD_REQUEST);
        }
        Account newAccount = new Account(id,
                newAccountRequest.getAccountName(),
                Long.toString(accountNumber),
                bCryptPasswordEncoder.encode(newAccountRequest.getAccountPassword()),
                newAccountRequest.getInitialDeposit()
        );
        accounts.add(newAccount);
        log.info("Account details: {}", accounts);
        return new ResponseEntity<>(new TransactionResponse(200,true,"account successfully created"),HttpStatus.OK);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return checkAccount(accounts, accountNumber);
    }

    @Override
    public ResponseEntity<?> accountLogin(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getAccountNumber(), loginRequest.getAccountPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(true, jwt));
    }


    private Account checkAccount(List<Account> accounts, String accountNumber){

        for(Account account: accounts){
            if(account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }

        return null;
    }


}
