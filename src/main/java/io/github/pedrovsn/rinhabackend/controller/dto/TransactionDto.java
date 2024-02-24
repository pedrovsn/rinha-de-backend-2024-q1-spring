package io.github.pedrovsn.rinhabackend.controller.dto;

import java.time.LocalDateTime;

public record TransactionDto(
        int valor,
        String tipo,
        String descricao,
        LocalDateTime realizadaEm
) {
}
