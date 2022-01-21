package com.maryafolabi.bankapp.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    String accountNumber;
    String accountPassword;
}
