package com.bank.accounting.business.service;

import com.bank.accounting.model.Account;
import com.bank.accounting.model.Customer;
import com.bank.accounting.model.OperationType;

/**
 * @author M.OMRI
 */

public interface AccountManagementService {
    /**
     * Deposit an amount of money to customer account.
     *
     * @param customer      a given customer.
     * @param operationType operation of type 'DEPOSIT'.
     * @param amount        the amount of money to deposit.
     */
    Account updateAccount(Customer customer, Double amount, OperationType operationType);
}
