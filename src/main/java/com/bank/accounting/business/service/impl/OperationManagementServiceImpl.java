package com.bank.accounting.business.service.impl;

import com.bank.accounting.business.service.OperationManagementService;
import com.bank.accounting.model.Operation;
import com.bank.accounting.model.OperationType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author M.OMRI
 */

public class OperationManagementServiceImpl implements OperationManagementService {
    private final List<Operation> operations = new ArrayList<>();

    @Override
    public void addNewOperation(OperationType operationType, Double amount, Double accountBalance) {
        if ((OperationType.DEPOSIT != operationType)
                && (OperationType.WITHDRAWAL != operationType)) {
            throw new IllegalArgumentException("Unsupported operation was provided");
        }

        operations.add(Operation.builder()
                .amount(amount)
                .balance(accountBalance)
                .date(LocalDateTime.now())
                .type(operationType)
                .build());
    }

    @Override
    public List<Operation> getOperationsHistory() {
        return operations;
    }
}
