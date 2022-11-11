package com.bank.accounting.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author M.OMRI
 */

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Account {
    private long id;
    private String accountNumber;
    @Builder.Default
    private Double balance = 0.0;
}
