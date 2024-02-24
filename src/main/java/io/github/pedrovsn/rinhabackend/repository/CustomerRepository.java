package io.github.pedrovsn.rinhabackend.repository;

import io.github.pedrovsn.rinhabackend.domain.Customer;

public interface CustomerRepository {
    Customer findCustomerById(int customerId);

    void updateCustomer(Customer customer);
}
