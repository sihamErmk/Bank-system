package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.OperationDto;
import com.miniproject.Banking_app.entity.Account;
import com.miniproject.Banking_app.entity.Operation;

import java.util.List;

public interface OperationService {

    Account effectuerVersement(OperationDto dto);
    Account effectuerRetrait(OperationDto dto);
    boolean effectuerVirement(OperationDto dto);

    List<Operation> findByClientAccountNumber(String AccountNumber);
}
