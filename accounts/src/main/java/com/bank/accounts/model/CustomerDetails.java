package com.bank.accounts.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Stephane Nganou
 */
public record CustomerDetails(Accounts accounts, List<Loans> loans, List<Cards> cards) {
    public CustomerDetails {
        Objects.requireNonNull(accounts, "accounts should not be null.");
    }
}
