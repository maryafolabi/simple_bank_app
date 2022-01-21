package com.maryafolabi.bankapp.payload.request;

import lombok.Data;

@Data
public class WithdrawalRequest {
    String accountNumber;
    String accountPassword;
    Double withdrawnAmount;
}
