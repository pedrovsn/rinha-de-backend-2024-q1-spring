package io.github.pedrovsn.rinhabackend.repository.jdbc;

import io.github.pedrovsn.rinhabackend.domain.Customer;
import io.github.pedrovsn.rinhabackend.repository.CustomerRepository;
import io.github.pedrovsn.rinhabackend.repository.jdbc.mapper.CustomerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerJdbcRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer findCustomerById(int customerId) {
        return jdbcTemplate.queryForObject("""
                SELECT id, name, credit_limit, balance FROM customer WHERE id = ? FOR UPDATE
                """, new CustomerRowMapper(), customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(
                "UPDATE customer SET balance = ? WHERE id = ?",
                customer.balance(),
                customer.id()
                );
    }
}
