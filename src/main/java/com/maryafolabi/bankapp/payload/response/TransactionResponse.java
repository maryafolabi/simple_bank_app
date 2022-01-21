package com.maryafolabi.bankapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    int responseCode;
    boolean success;
    String message;
}
