package com.maryafolabi.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.Id;


@Data
@AllArgsConstructor
public class Account {
    @Id
    @JsonIgnore
    private Long id;
    private String accountName;
    private String accountNumber;
    @JsonIgnore
    private String password;
    private Double balance;
}
