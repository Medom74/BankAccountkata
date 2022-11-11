package com.bank.accounting.business.impl;

import com.bank.accounting.business.service.AccountManagementService;
import com.bank.accounting.business.service.OperationManagementService;
import com.bank.accounting.business.service.impl.AccountManagementServiceImpl;
import com.bank.accounting.business.service.impl.OperationManagementServiceImpl;
import com.bank.accounting.business.service.strategy.Deposit;
import com.bank.accounting.business.service.strategy.OperationStrategy;
import com.bank.accounting.business.service.strategy.Withdraw;
import com.bank.accounting.model.Account;
import com.bank.accounting.model.Customer;
import com.bank.accounting.model.OperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link AccountManagementServiceImpl}
 *
 * @author M.OMRI
 */
class AccountManagementServiceImplTest {

    private AccountManagementService accountManagementServiceMock;

    @Test
    void testUpdateAccountWithSuccess_Deposit() {
        // Given
        initMocks(OperationType.DEPOSIT);
        Customer customer = getCustomer();
        // Then
        Account account = accountManagementServiceMock.updateAccount(customer, 100.0, OperationType.DEPOSIT);
        assertEquals(100.0, account.getBalance());
        assertNotNull(account);
    }

    @Test
    void testUpdateAccountWithAccountIsNull_Deposit() {
        // Given
        initMocks(OperationType.DEPOSIT);
        Customer customer = getCustomer();
        customer.setAccount(null);
        // Then
        NullPointerException thrown = assertThrows(NullPointerException.class, () ->
                accountManagementServiceMock.updateAccount(customer, 100.0, OperationType.DEPOSIT));
        assertEquals("customer account cannot be null", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-120.0, 0.0})
    void testUpdateAccountWithAmountIsNegativeAndZero_Deposit(Double amount) {
        // Given
        initMocks(OperationType.DEPOSIT);
        Customer customer = getCustomer();
        // Then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                accountManagementServiceMock.updateAccount(customer, amount, OperationType.DEPOSIT));
        assertEquals("Amount to deposit cannot be negative or 0", thrown.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getAccountParams")
    void testUpdateAccountWithCustomerAndOrAmountIsNull_Deposit(Customer customer, Double amount) {
        // Given
        initMocks(OperationType.DEPOSIT);
        // Then
        NullPointerException thrown = assertThrows(NullPointerException.class, () ->
                accountManagementServiceMock.updateAccount(customer, amount, OperationType.DEPOSIT));
        assertEquals("customer and/or amount cannot be null", thrown.getMessage());
    }

    @Test
    void testUpdateAccountWithSuccess_Withdraw() {
        // Given
        initMocks(OperationType.DEPOSIT);
        Customer customer = getCustomer();
        accountManagementServiceMock.updateAccount(customer, 500.0, OperationType.DEPOSIT);
        // Then
        initMocks(OperationType.WITHDRAWAL);
        Account account = accountManagementServiceMock.updateAccount(customer, 150.0, OperationType.WITHDRAWAL);
        assertEquals(350.0, account.getBalance());
        assertNotNull(account);
    }

    @Test
    void testUpdateAccountWithAccountIsNull_Withdraw() {
        // Given
        initMocks(OperationType.WITHDRAWAL);
        Customer customer = getCustomer();
        customer.setAccount(null);
        // Then
        NullPointerException thrown = assertThrows(NullPointerException.class, () ->
                accountManagementServiceMock.updateAccount(customer, 100.0, OperationType.WITHDRAWAL));
        assertEquals("customer account cannot be null", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-120.0, 0.0})
    void testUpdateAccountWithAmountIsNegativeAndZero_Withdraw(Double amount) {
        // Given
        initMocks(OperationType.WITHDRAWAL);
        Customer customer = getCustomer();
        // Then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                accountManagementServiceMock.updateAccount(customer, amount, OperationType.WITHDRAWAL));
        assertEquals("Amount to deposit cannot be negative or 0", thrown.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getAccountParams")
    void testUpdateAccountWithCustomerAndOrAmountIsNull_Withdraw(Customer customer, Double amount) {
        // Given
        initMocks(OperationType.WITHDRAWAL);
        // Then
        NullPointerException thrown = assertThrows(NullPointerException.class, () ->
                accountManagementServiceMock.updateAccount(customer, amount, OperationType.WITHDRAWAL));
        assertEquals("customer and/or amount cannot be null", thrown.getMessage());
    }

    private Customer getCustomer() {
        return Customer.builder()
                .id(1L)
                .account(getAccount())
                .name("Customer name")
                .account(getAccount())
                .build();
    }

    private Account getAccount() {
        return Account.builder()
                .id(1L)
                .accountNumber("ACC0001")
                .balance(0.0)
                .build();
    }

    private static Stream<Arguments> getAccountParams() {
        return Stream.of(
                Arguments.arguments(null, 100.0),
                Arguments.arguments(Customer.builder().build(), null)
        );
    }

    private void initMocks(OperationType operationType) {
        OperationManagementService operationManagementServiceMock = new OperationManagementServiceImpl();
        OperationStrategy operationStrategyMock;
        if (OperationType.DEPOSIT == operationType) {
            operationStrategyMock = Deposit.builder().build();
            accountManagementServiceMock = AccountManagementServiceImpl.builder()
                    .operationManagementService(operationManagementServiceMock)
                    .operationStrategy(operationStrategyMock)
                    .build();

        } else if (OperationType.WITHDRAWAL == operationType) {
            operationStrategyMock = Withdraw.builder().build();
            accountManagementServiceMock = AccountManagementServiceImpl.builder()
                    .operationManagementService(operationManagementServiceMock)
                    .operationStrategy(operationStrategyMock)
                    .build();

        }
    }
}
