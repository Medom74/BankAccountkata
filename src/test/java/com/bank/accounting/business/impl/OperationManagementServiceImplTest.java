package com.bank.accounting.business.impl;

import com.bank.accounting.business.service.OperationManagementService;
import com.bank.accounting.business.service.impl.OperationManagementServiceImpl;
import com.bank.accounting.model.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Tests for {@link OperationManagementServiceImpl}
 *
 * @author M.OMRI
 */
class OperationManagementServiceImplTest {
    private OperationManagementService operationManagementServiceMock;

    @BeforeEach
    void init() {
        operationManagementServiceMock = new OperationManagementServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("getOperationTypes")
    void testAddNewOperationwWithSucess(OperationType operationType) {
        // Given
        Double amount = 100.0;
        Double balance = 0.0;
        //Then
        operationManagementServiceMock.addNewOperation(operationType, amount, balance);
        assertEquals(1, operationManagementServiceMock.getOperationsHistory().size());
        assertEquals(100.00, operationManagementServiceMock.getOperationsHistory().get(0).getAmount());
    }

    @Test
    void testAddNewOperationwWithFailure_Deposit() {
        // Given
        Double amount = 100.0;
        Double balance = 0.0;
        //Then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                operationManagementServiceMock.addNewOperation(null, amount, balance));
        assertEquals("Unsupported operation was provided", thrown.getMessage());
    }

    private static Stream<OperationType> getOperationTypes() {
        return Stream.of(OperationType.DEPOSIT, OperationType.WITHDRAWAL);
    }

}
