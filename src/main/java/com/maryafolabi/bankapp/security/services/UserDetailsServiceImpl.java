package com.maryafolabi.bankapp.security.services;

import com.maryafolabi.bankapp.model.Account;
import com.maryafolabi.bankapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Account account = accountService.findByAccountNumber(accountNumber);
        return UserDetailsImpl.build(account);
    }
}
