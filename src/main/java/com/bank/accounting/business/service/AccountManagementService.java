package com.bank.accounting.business.service;

import com.bank.accounting.model.Account;
import com.bank.accounting.model.Customer;
import com.bank.accounting.model.OperationType;

/**
 * @author M.OMRI
 */

public interface AccountManagementService {
    /**
     * Withdraw or deposit an amount of money to customer account.
     *
     * @param customer      a given customer.
     * @param operationType operation of type 'DEPOSIT' or 'WITHDRAW'.
     * @param amount        the amount of money to deposit or withdraw.
     */
    Account updateAccount(Customer customer, Double amount, OperationType operationType);
}
