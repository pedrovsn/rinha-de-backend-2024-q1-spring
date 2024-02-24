package io.github.pedrovsn.rinhabackend.service;

import io.github.pedrovsn.rinhabackend.domain.CustomerCurrentStatus;
import io.github.pedrovsn.rinhabackend.domain.CustomerTransactions;

public interface TransactionService {

    CustomerCurrentStatus placeTransaction(int customerId, String type, int amount, String description);

    CustomerTransactions getCustomerTransactions(int customerId);
}
