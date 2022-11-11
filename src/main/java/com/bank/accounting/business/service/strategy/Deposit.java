package com.bank.accounting.business.service.strategy;

import com.bank.accounting.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author M.OMRI
 */

@Builder
@AllArgsConstructor
public class Deposit implements OperationStrategy {
    /**
     * Deposit an amount of money to customer account.
     *
     * @param account customer's account.
     * @param amount  the amount to deposit.
     */
    @Override
    public void updateAccount(Account account, Double amount) {
        account.setBalance(account.getBalance() + amount);
    }
}
