package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.OperationDto;
import com.miniproject.Banking_app.entity.Account;
import com.miniproject.Banking_app.entity.Operation;
import com.miniproject.Banking_app.enums.AccountStatus;
import com.miniproject.Banking_app.enums.TypeOperation;
import com.miniproject.Banking_app.repository.AccountRepository;
import com.miniproject.Banking_app.repository.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OperationServiceImpl implements OperationService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    public OperationServiceImpl(final AccountRepository accountRepository, final OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    @Transactional
    public Account effectuerVersement(OperationDto dto) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(dto.getAccountNumber());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getStatus().equals(AccountStatus.ACTIVATED)) {
                // Create and set up a new operation
                Operation operation = new Operation();
                operation.setDateOperation(new Date());
                operation.setMontant(dto.getMontant());
                operation.setTypeOperation(TypeOperation.VERSEMENT);
                operation.setAccount(account);
                operation.setNumOperation(generateOperationNumber());

                // Save the operation
                operationRepository.save(operation);

                // Update the account balance
                account.setBalance(account.getBalance() + dto.getMontant());
                accountRepository.save(account);

                return account;
            } else {
                throw new RuntimeException("Le compte est suspendu");
            }
        } else {
            throw new RuntimeException("Ce compte n'existe pas");
        }
    }

    @Override
    @Transactional
    public Account effectuerRetrait(OperationDto dto) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(dto.getAccountNumber());

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getStatus().equals(AccountStatus.ACTIVATED)) {
                if (account.getBalance() >= dto.getMontant()) {
                    // Create and set up a new operation for withdrawal
                    Operation operation = new Operation();
                    operation.setDateOperation(new Date());
                    operation.setMontant(dto.getMontant());
                    operation.setTypeOperation(TypeOperation.RETRAIT);
                    operation.setAccount(account);
                    operation.setNumOperation(generateOperationNumber());

                    // Save the operation
                    operationRepository.save(operation);

                    // Deduct the amount from account balance
                    account.setBalance(account.getBalance() - dto.getMontant());
                    accountRepository.save(account);

                    return account; // Return the updated account
                } else {
                    throw new RuntimeException("Solde insuffisant pour effectuer le retrait");
                }
            } else {
                throw new RuntimeException("Le compte est suspendu");
            }
        } else {
            throw new RuntimeException("Ce compte n'existe pas");
        }
    }

    @Override
    @Transactional
    public boolean effectuerVirement(OperationDto dto) {
        // Perform the withdrawal from the source account
        Account sourceAccount = effectuerRetrait(new OperationDto(dto.getAccountNumber(), null, dto.getMontant()));

        if (sourceAccount != null) {
            // Perform the deposit to the destination account
            OperationDto destinationDto = new OperationDto(dto.getAccountNumberDest(), null, dto.getMontant());
            effectuerVersement(destinationDto);
            return true;
        }
        return false;
    }

    private static long generateOperationNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            sb.append("0");
        }
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(2));
        }
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }

        return Long.parseLong(sb.toString());
    }

    public List<Operation> findByClientAccountNumber(String accountNumber) {
        List<Operation> list = new ArrayList<>();
        for (Operation operation : operationRepository.findAll()) {
            if (operation.getAccount() != null && operation.getAccount().getAccountNumber().equals(accountNumber)) {
                list.add(operation);
            }
        }
        return list;
    }

}
