package com.bank.accounting;

import com.bank.accounting.business.service.AccountManagementService;
import com.bank.accounting.business.service.OperationManagementService;
import com.bank.accounting.business.service.impl.AccountManagementServiceImpl;
import com.bank.accounting.business.service.impl.OperationManagementServiceImpl;
import com.bank.accounting.business.service.strategy.Deposit;
import com.bank.accounting.business.service.strategy.Withdraw;
import com.bank.accounting.model.Account;
import com.bank.accounting.model.Customer;
import com.bank.accounting.model.OperationType;

class App {
    public static void main(String[] args) {
        OperationManagementService operationManagementService = new OperationManagementServiceImpl();

        AccountManagementService accountManagementService = AccountManagementServiceImpl.builder()
                .operationStrategy(Deposit.builder().build())
                .operationManagementService(operationManagementService)
                .build();

        Account account = Account.builder()
                .id(1L)
                .accountNumber("0001FK001")
                .build();
        Customer customer = Customer.builder()
                .id(1L)
                .name("Customer name")
                .account(account)
                .build();

        accountManagementService.updateAccount(customer, 100.0, OperationType.DEPOSIT);
        accountManagementService.updateAccount(customer, 300.0, OperationType.DEPOSIT);

        accountManagementService = AccountManagementServiceImpl.builder()
                .operationStrategy(Withdraw.builder().build())
                .operationManagementService(operationManagementService)
                .build();

        accountManagementService.updateAccount(customer, 50.0, OperationType.WITHDRAWAL);
        accountManagementService.updateAccount(customer, 350.0, OperationType.WITHDRAWAL);
        System.out.println(operationManagementService.getOperationsHistory());
    }
}
