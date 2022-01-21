package com.maryafolabi.bankapp.payload.request;

import lombok.Data;

@Data
public class DepositRequest {
    private String accountNumber;
    private Double amount;
}
