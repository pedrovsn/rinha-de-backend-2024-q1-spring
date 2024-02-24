package io.github.pedrovsn.rinhabackend.controller.dto;

public record CreateTransactionResponse(
        int saldo,
        int limite
) {
}
