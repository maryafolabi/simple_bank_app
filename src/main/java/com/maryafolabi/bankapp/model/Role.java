package com.maryafolabi.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
public class Role {

    @Enumerated(EnumType.STRING)
    private ERole name;

}


