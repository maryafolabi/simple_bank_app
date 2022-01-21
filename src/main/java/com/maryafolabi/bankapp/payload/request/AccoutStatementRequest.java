package com.maryafolabi.bankapp.payload.request;

import lombok.Data;

@Data
public class AccoutStatementRequest {
    private String accountName;
    private String accountNumber;
    private String password;
}
