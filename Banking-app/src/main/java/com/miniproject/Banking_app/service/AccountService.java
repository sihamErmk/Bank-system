package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.AccountDto;
import com.miniproject.Banking_app.entity.Account;
import com.miniproject.Banking_app.entity.CourantAccount;
import com.miniproject.Banking_app.entity.SavingAccount;

import java.util.List;

public interface AccountService {

    void  createAccount(AccountDto accountDto);

    List<SavingAccount> findSavingAccounts();
    List<CourantAccount> findCourantAccounts();
    Account findOne(String accountNumber);


    boolean activeAccount(String accountNumber);

    boolean suspendAccount(String accountNumber);

    List<Account> findAllAccounts();





}
