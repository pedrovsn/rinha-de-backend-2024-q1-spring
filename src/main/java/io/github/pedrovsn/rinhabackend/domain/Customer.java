package io.github.pedrovsn.rinhabackend.domain;

public record Customer(
        Integer id,
        String name,
        int creditLimit,
        int balance
) {
}
