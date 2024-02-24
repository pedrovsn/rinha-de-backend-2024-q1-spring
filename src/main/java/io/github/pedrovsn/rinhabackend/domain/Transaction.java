package io.github.pedrovsn.rinhabackend.domain;

import java.time.LocalDateTime;

public record Transaction(
        Integer id,
        int customerId,
        int amount,
        String type,
        String description,
        LocalDateTime createdAt
) {
}
