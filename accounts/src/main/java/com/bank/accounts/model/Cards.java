package com.bank.accounts.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

public record Cards(long cardId, long customerId, String cardNumber,
                    String cardType, int totalLimit, int amountUsed,
                    int availableAmount, LocalDate createDate) {

    public Cards {
        Objects.requireNonNull(cardType, "cardType should not be null.");
    }

}
