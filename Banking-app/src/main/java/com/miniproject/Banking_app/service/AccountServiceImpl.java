package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.AccountDto;
import com.miniproject.Banking_app.entity.Account;
import com.miniproject.Banking_app.entity.Client;
import com.miniproject.Banking_app.entity.CourantAccount;
import com.miniproject.Banking_app.entity.SavingAccount;
import com.miniproject.Banking_app.enums.AccountStatus;
import com.miniproject.Banking_app.repository.AccountRepository;
import com.miniproject.Banking_app.repository.ClientRepository;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import java.util.*;


@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    AccountServiceImpl(final AccountRepository accountRepository, final ClientRepository clientRepository){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;

    }
    @Override
    public void createAccount(AccountDto accountDto) {
        Optional<Client> clientOptional = this.clientRepository.findById(accountDto.getId_Client());
        if (clientOptional.isPresent() && (accountDto.getDecouvert()>0) && (accountDto.getInteretRate()==0)){
            CourantAccount courantAccount =new CourantAccount();
            courantAccount.setCreatedDate(new Date());
            courantAccount.setBalance(accountDto.getBalance());
            courantAccount.setDecouvert(accountDto.getDecouvert());
            courantAccount.setClient(clientOptional.get());
            courantAccount.setStatus(AccountStatus.ACTIVATED);
            courantAccount.setAccountNumber(generateAccountNumber());
            this.accountRepository.save(courantAccount);

        }
        if (clientOptional.isPresent() && (accountDto.getDecouvert()==0) && (accountDto.getInteretRate()>0)){
            SavingAccount savingAccount =new SavingAccount();
            savingAccount.setCreatedDate( new Date());
            savingAccount.setBalance(accountDto.getBalance());
            savingAccount.setInteretRate(accountDto.getInteretRate());
            savingAccount.setClient(clientOptional.get());
            savingAccount.setStatus(AccountStatus.ACTIVATED);
            savingAccount.setAccountNumber(generateAccountNumber());
            this.accountRepository.save(savingAccount);

        }

    }

    private static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb= new StringBuilder();
        //les 4 pre chiffres sont 0
        for(int i =0;i<4;i++){
            sb.append("0");
        }
         //  les 4 suiv  chiff  sont 0 ou 1

        for(int i =0;i<4;i++){
            sb.append(random.nextInt(2));
        }
        //les 10 derniere  chiff  sont aleatoires
        for(int i =0;i<10;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public List<SavingAccount> findSavingAccounts() {
        List<SavingAccount> list = new ArrayList<>();
        for(Account account:accountRepository.findAll()){
            if(account instanceof SavingAccount){
                list.add((SavingAccount) account);
            }
        }
        return list;
    }


    @Override
    public List<CourantAccount> findCourantAccounts() {
        List<CourantAccount> list = new ArrayList<>();
        for(Account account:accountRepository.findAll()){
            if(account instanceof CourantAccount){
                list.add((CourantAccount) account);
            }
        }
        return  list;

    }

    @Override
    public Account findOne(String accountNumber) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new RuntimeException("Account not found for account number: " + accountNumber);
        }
    }





    @Override
    public boolean activeAccount(String accountNumber) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent() && accountOptional.get().getStatus().equals(AccountStatus.SUSPENDED)) {
            Account account = accountOptional.get();
            account.setStatus(AccountStatus.ACTIVATED);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean suspendAccount(String accountNumber) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent() && accountOptional.get().getStatus().equals(AccountStatus.ACTIVATED)) {
            Account account = accountOptional.get();
            account.setStatus(AccountStatus.SUSPENDED);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }



}
