package com.bank.accounting.business.service.strategy;

import com.bank.accounting.exception.InsufficientAccountBalanceException;
import com.bank.accounting.model.Account;
import lombok.Builder;

/**
 * @author M.OMRI
 */

@Builder
public class Withdraw implements OperationStrategy {
    /**
     * Withdraw an amount of money to customer account.
     *
     * @param account customer's account.
     * @param amount  the amount to withdraw.
     */
    @Override
    public void updateAccount(Account account, Double amount) {
        if (account.getBalance() < amount) {
            throw new InsufficientAccountBalanceException("account balance is insufficient");
        }
        account.setBalance(account.getBalance() - amount);
    }
}
