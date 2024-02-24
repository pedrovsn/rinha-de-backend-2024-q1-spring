package io.github.pedrovsn.rinhabackend.repository;

import io.github.pedrovsn.rinhabackend.domain.Transaction;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);

    List<Transaction> findTransactionsByCustomerId(int customerId, int limit);
}
