package io.github.pedrovsn.rinhabackend.repository.jdbc.mapper;

import io.github.pedrovsn.rinhabackend.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("credit_limit"),
                rs.getInt("balance")
        );
    }
}
