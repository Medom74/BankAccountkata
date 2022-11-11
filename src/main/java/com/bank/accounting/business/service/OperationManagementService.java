package com.bank.accounting.business.service;

import com.bank.accounting.model.Operation;
import com.bank.accounting.model.OperationType;

import java.util.List;

/**
 * @author M.OMRI
 */

public interface OperationManagementService {
    /**
     * Add a new operation of type 'DEPOSIT' or 'WITHDRAW'.
     *
     * @param operationType  Operation type : 'DEPOSIT' or 'WITHDRAW'.
     * @param amount         the amount of money to deposit or withdraw.
     * @param accountBalance customer account's balance
     */
    void addNewOperation(OperationType operationType, Double amount, Double accountBalance);

    /**
     * Retrieve the list of operations made by the customer.
     *
     * @return List of {@link Operation}
     */
    List<Operation> getOperationsHistory();
}
