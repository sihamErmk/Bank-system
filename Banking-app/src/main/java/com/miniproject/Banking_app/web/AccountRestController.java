package com.miniproject.Banking_app.web;

import com.miniproject.Banking_app.dto.AccountDto;
import com.miniproject.Banking_app.entity.Account;
import com.miniproject.Banking_app.entity.CourantAccount;
import com.miniproject.Banking_app.entity.SavingAccount;
import com.miniproject.Banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountRestController {
    private final AccountService accountService;

    AccountRestController(final AccountService accountService){
        this.accountService = accountService;

    }
    @PostMapping("/accounts")
    void createAccount(@RequestBody AccountDto accountDto){

        this.accountService.createAccount(accountDto);
    }
    @GetMapping("/accounts/type/{type}")
    public ResponseEntity<?> findAll(@PathVariable("type") String type) {
        List<?> accounts;
        if (type.equals("CA")) {
            accounts = this.accountService.findCourantAccounts();
            if (accounts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Courant accounts found");
            }
        } else if (type.equals("SA")) {
            accounts = this.accountService.findSavingAccounts();
            if (accounts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Saving accounts found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid account type");
        }
        return ResponseEntity.ok(accounts);
    }



    @GetMapping("/accounts/{accountNumber}/{type}")
    ResponseEntity<?> findAccount(@PathVariable("accountNumber")String accountNumber
            ,@PathVariable("type") String type){
        Account account = this.accountService.findOne(accountNumber);
        if(type.equals("CA") && (account instanceof CourantAccount)){
            return  ResponseEntity.ok((CourantAccount)account);

        }
        if(type.equals("SA") && (account instanceof SavingAccount)){
            return  ResponseEntity.ok((SavingAccount)account);
        }
        return null;

    }
    @GetMapping("/accounts/active/{accountNumber}")
    boolean activeAccount(@PathVariable("accountNumber") String accountNumber){
        return this.accountService.activeAccount(accountNumber);
    }

    @GetMapping("/accounts/suspend/{accountNumber}")
    boolean suspendAccount(@PathVariable("accountNumber") String accountNumber){
        return  this.accountService.suspendAccount(accountNumber);
    }


}