package com.maryafolabi.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
public class Transaction {
   private Date transactionDate;
   @Enumerated(EnumType.STRING)
   private TransactionType transactionType;
   private String narration;
   private Double amount;
   @ManyToOne
   private Account account;
}
