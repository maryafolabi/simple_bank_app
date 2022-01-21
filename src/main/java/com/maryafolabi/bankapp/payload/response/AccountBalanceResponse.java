package com.maryafolabi.bankapp.payload.response;

import com.maryafolabi.bankapp.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountBalanceResponse {
    private int responseCode;
    private boolean success;
    private String message;
    private Account account;

    public AccountBalanceResponse(int responseCode, boolean success, String message, Account account) {
        this.responseCode = responseCode;
        this.success = success;
        this.message = message;
        this.account = account;
    }

    public AccountBalanceResponse(int responseCode, boolean success, String message) {
        this.responseCode = responseCode;
        this.success = success;
        this.message = message;
    }
}
