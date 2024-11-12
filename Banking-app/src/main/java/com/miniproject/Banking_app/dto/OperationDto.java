package com.miniproject.Banking_app.dto;

import com.miniproject.Banking_app.entity.Account;
import com.miniproject.Banking_app.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private Long id;
    private  double montant;
    private String accountNumber;
    private String accountNumberDest;

    public OperationDto(String accountNumber, String accountNumberDest, double montant) {
        this.accountNumber = accountNumber;
        this.accountNumberDest = accountNumberDest;
        this.montant = montant;
    }

    // Constructor for a single account number (for withdrawals or deposits)
    public OperationDto(String accountNumber, double montant) {
        this.accountNumber = accountNumber;
        this.montant = montant;
    }


}
