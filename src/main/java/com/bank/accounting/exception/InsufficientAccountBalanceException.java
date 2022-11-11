package com.bank.accounting.exception;

/**
 * Exception raised when there is insufficient balance in user account.
 *
 * @author M.OMRI
 */

public class InsufficientAccountBalanceException extends RuntimeException {
    private String message;

    public InsufficientAccountBalanceException(String message) {
        this.message = message;
    }
}
