package com.bank.accounting.business.service.impl;

import com.bank.accounting.business.service.AccountManagementService;
import com.bank.accounting.business.service.strategy.OperationStrategy;
import com.bank.accounting.model.Account;
import com.bank.accounting.model.Customer;
import com.bank.accounting.model.OperationType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class AccountManagementServiceImpl implements AccountManagementService {
    private final OperationStrategy operationStrategy;

    @Override
    public Account updateAccount(Customer customer, Double amount, OperationType operationType) {
        checkOperation(customer, amount);
        Account account = getCustomerAccount(customer);
        operationStrategy.updateAccount(account, amount);
        return account;
    }

    private Account getCustomerAccount(Customer customer) {
        if (customer.getAccount() == null) {
            throw new NullPointerException("customer account cannot be null");
        }
        return customer.getAccount();
    }

    private void checkOperation(Customer customer, Double amount) {
        if (customer == null || amount == null) {
            throw new NullPointerException("customer and/or amount cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to deposit cannot be negative or 0");
        }
    }
}
