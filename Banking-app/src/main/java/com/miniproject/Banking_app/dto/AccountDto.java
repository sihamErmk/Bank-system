package com.miniproject.Banking_app.dto;

import com.miniproject.Banking_app.entity.Client;
import com.miniproject.Banking_app.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private double balance;
    private long id_Client;
    private double decouvert;
    private double interetRate;

}
