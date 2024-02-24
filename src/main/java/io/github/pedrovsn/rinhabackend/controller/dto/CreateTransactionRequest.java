package io.github.pedrovsn.rinhabackend.controller.dto;

public record CreateTransactionRequest(
        int valor,
        String tipo,
        String descricao
) {
}
