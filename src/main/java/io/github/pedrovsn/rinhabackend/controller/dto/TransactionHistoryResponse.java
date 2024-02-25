package io.github.pedrovsn.rinhabackend.controller.dto;

import com.fasterxml.jackson.annotation.*;
import java.util.*;

public final class TransactionHistoryResponse {

    private final BalanceDto saldo;
    private final List<TransactionDto> ultimasTransacoes;

    @JsonCreator
    public TransactionHistoryResponse(
            BalanceDto saldo,
            List<TransactionDto> ultimasTransacoes
    ) {
        this.saldo = saldo;
        this.ultimasTransacoes = ultimasTransacoes;
    }

    public BalanceDto getSaldo() {
        return saldo;
    }

    @JsonProperty("ultimas_transacoes")
    public List<TransactionDto> getUltimasTransacoes() {
        return ultimasTransacoes;
    }

}
