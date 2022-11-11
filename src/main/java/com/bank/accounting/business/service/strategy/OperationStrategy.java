package com.bank.accounting.business.service.strategy;

import com.bank.accounting.model.Account;

/**
 * @author M.OMRI
 */

public interface OperationStrategy {
    void updateAccount(Account account, Double amount);
}
