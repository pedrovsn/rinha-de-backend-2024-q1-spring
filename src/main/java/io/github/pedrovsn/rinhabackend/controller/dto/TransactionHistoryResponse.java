package io.github.pedrovsn.rinhabackend.controller.dto;

import java.util.List;

public record TransactionHistoryResponse(
        int saldo,
        int limite,
        List<TransactionDto> transacoes
) {
}
