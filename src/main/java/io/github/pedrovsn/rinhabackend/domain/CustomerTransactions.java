package io.github.pedrovsn.rinhabackend.domain;

import java.util.List;

public record CustomerTransactions(
        Integer customerId,
        int creditLimit,
        int balance,
        List<Transaction> transactions
) {
}
