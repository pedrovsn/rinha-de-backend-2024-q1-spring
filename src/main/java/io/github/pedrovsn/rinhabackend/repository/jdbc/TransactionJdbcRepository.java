package io.github.pedrovsn.rinhabackend.repository.jdbc;

import io.github.pedrovsn.rinhabackend.domain.Transaction;
import io.github.pedrovsn.rinhabackend.repository.TransactionRepository;
import java.sql.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionJdbcRepository implements TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Transaction transaction) {
        jdbcTemplate.update("""
                        INSERT INTO transaction (customer_id, amount, type, description, created_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                transaction.customerId(),
                transaction.amount(),
                transaction.type().charAt(0),
                transaction.description(),
                transaction.createdAt()
        );
    }

//    @Override
//    public void save(Transaction transaction) {
//        jdbcTemplate.update(
//                "CALL update_balance_and_create_transaction(?,?,?,?)",
//                transaction.customerId(),
//                transaction.amount(),
//                transaction.type(),
//                transaction.description()
//        );
//    }

    @Override
    public List<Transaction> findTransactionsByCustomerId(int customerId, int limit) {
        return jdbcTemplate.query("""
                SELECT id, customer_id, amount, type, description, created_at
                FROM transaction
                WHERE customer_id = ? ORDER BY id DESC LIMIT ?
                """, new Object[]{customerId, limit}, rs -> {
                    List<Transaction> transactions = new ArrayList<>();
                    while (rs.next()) {
                        transactions.add(
                                new Transaction(
                                        rs.getInt("id"),
                                        rs.getInt("customer_id"),
                                        rs.getInt("amount"),
                                        rs.getString("type"),
                                        rs.getString("description"),
                                        rs.getTimestamp("created_at").toLocalDateTime()
                                )
                        );
                    }

                    return transactions;
                });
    }
}
