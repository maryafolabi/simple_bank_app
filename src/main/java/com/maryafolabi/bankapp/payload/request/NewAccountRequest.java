package com.maryafolabi.bankapp.payload.request;

import lombok.Data;

@Data
public class NewAccountRequest {
    private String accountName;
    private String accountPassword;
    private Double initialDeposit;
}
