package io.github.pedrovsn.rinhabackend.service.impl;

import io.github.pedrovsn.rinhabackend.domain.Customer;
import io.github.pedrovsn.rinhabackend.domain.CustomerCurrentStatus;
import io.github.pedrovsn.rinhabackend.domain.CustomerTransactions;
import io.github.pedrovsn.rinhabackend.domain.Transaction;
import io.github.pedrovsn.rinhabackend.exception.*;
import io.github.pedrovsn.rinhabackend.repository.CustomerRepository;
import io.github.pedrovsn.rinhabackend.repository.TransactionRepository;
import io.github.pedrovsn.rinhabackend.service.TransactionService;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final CustomerRepository customerRepository;

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(
            CustomerRepository customerRepository,
            TransactionRepository transactionRepository
    ) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    @Override
    public CustomerCurrentStatus placeTransaction(
            int customerId,
            String type,
            int amount,
            String description
    ) {
        if (!isValidCustomerId(customerId)) {
            throw new CustomerNotFoundException("Invalid customerId");
        }

        if (!isValidTransactionType(type)) {
            throw new InvalidAttributeException("Invalid transaction type");
        }

        if (!isValidDescription(description)) {
            throw new InvalidAttributeException("Invalid description");
        }

        Customer customer = customerRepository.findCustomerById(customerId);
        int currentBalance = customer.balance();

        switch (type) {
            case "c": {
                currentBalance += amount;
                break;
            }
            case "d": {
                int maxValue = currentBalance + customer.creditLimit();

                if (amount > maxValue) {
                    throw new InsufficientFundsException("Transaction amount exceeds customer limit");
                }

                currentBalance -= amount;
                break;
            }
        }

        customerRepository.updateCustomer(new Customer(
                customerId,
                customer.name(),
                customer.creditLimit(),
                currentBalance
        ));

        transactionRepository.save(
                new Transaction(
                        null,
                        customerId,
                        amount,
                        type,
                        description,
                        LocalDateTime.now()
                )
        );

        return new CustomerCurrentStatus(
                customer.creditLimit(),
                currentBalance
        );
    }

    @Override
    public CustomerTransactions getCustomerTransactions(int customerId) {
        if (!isValidCustomerId(customerId)) {
            throw new CustomerNotFoundException("Invalid customerId");
        }

        Customer customer = customerRepository.findCustomerById(customerId);
        List<Transaction> transactionsByCustomerId = transactionRepository
                .findTransactionsByCustomerId(customerId, 10);

        return new CustomerTransactions(
                customerId,
                customer.creditLimit(),
                customer.balance(),
                transactionsByCustomerId
        );
    }

    private boolean isValidCustomerId(int customerId) {
        return customerId >= 1 && customerId <= 5;
    }

    private boolean isValidTransactionType(String transactionType) {
        return "c".equals(transactionType) || "d".equals(transactionType);
    }

    private boolean isValidDescription(String description) {
        if (Objects.isNull(description)) {
            return false;
        }

        if (description.isBlank()) {
            return false;
        }

        if (description.length() > 10) {
            return false;
        }

        return true;
    }
}
