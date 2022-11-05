package com.bank.accounts.model;

import java.time.LocalDate;
import java.util.Objects;

public record Loans(long loanNumber, long customerId, LocalDate startDate,
                    String loanType, int totalLoan, int amountPaid,
                    int outstandingAmount, LocalDate createDate) {

    public Loans {
        Objects.requireNonNull(startDate, "startDate should not be null.");
        Objects.requireNonNull(loanType, "loanType should not be null.");
    }
}
