package com.maryafolabi.bankapp.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maryafolabi.bankapp.model.Account;
import com.maryafolabi.bankapp.model.ERole;
import com.maryafolabi.bankapp.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String accountNumber;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return accountNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDetailsImpl(String accountNumber, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Account account){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ROLE_USER));
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                account.getAccountNumber(),
                account.getPassword(),
                authorities);
    }


}