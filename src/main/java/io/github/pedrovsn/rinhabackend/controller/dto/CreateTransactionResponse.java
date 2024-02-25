package io.github.pedrovsn.rinhabackend.controller.dto;

import com.fasterxml.jackson.annotation.*;

public final class CreateTransactionResponse {

    private final int saldo;
    private final int limite;

    @JsonCreator
    public CreateTransactionResponse(
            int saldo,
            int limite
    ) {
        this.saldo = saldo;
        this.limite = limite;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getLimite() {
        return limite;
    }

}
