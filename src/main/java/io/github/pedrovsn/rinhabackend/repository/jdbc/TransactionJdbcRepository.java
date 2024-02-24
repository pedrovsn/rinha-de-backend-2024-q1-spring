package io.github.pedrovsn.rinhabackend.repository.jdbc;

import io.github.pedrovsn.rinhabackend.domain.Transaction;
import io.github.pedrovsn.rinhabackend.repository.TransactionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
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
                        INSERT INTO transaction (customer_id, amount, tipo, descricao, created_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                transaction.customerId(),
                transaction.amount(),
                transaction.type(),
                transaction.description(),
                transaction.createdAt()
        );
    }

    @Override
    public List<Transaction> findTransactionsByCustomerId(int customerId, int limit) {
        return jdbcTemplate.query("""
                SELECT id, customer_id, amount, tipo, descricao, created_at
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
                                        rs.getString("descricao"),
                                        rs.getTimestamp("created_at").toLocalDateTime()
                                )
                        );
                    }

                    return transactions;
                });
    }
}
