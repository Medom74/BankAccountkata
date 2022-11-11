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
public class Customer {
    private long id;
    private String name;
    private Account account;
}
