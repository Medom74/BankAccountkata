package com.bank.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author M.OMRI
 */

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Operation {
    private long id;
    private LocalDateTime date;
    private OperationType type;
    private Double amount;
    private Double balance;

    @Override
    public String toString() {
        return "\n Operation{" +
                "  Type : " + type +
                ", Date : " + date +
                ", amount : " + amount +
                ", Balance : " + balance +
                '}';
    }
}
