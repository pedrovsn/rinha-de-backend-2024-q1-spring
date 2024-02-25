package io.github.pedrovsn.rinhabackend.controller.dto;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDateTime;

public final class TransactionDto {

    private final int valor;
    private final String tipo;
    private final String descricao;
    private final LocalDateTime realizadaEm;

    @JsonCreator
    public TransactionDto(
            int valor,
            String tipo,
            String descricao,
            LocalDateTime realizadaEm
    ) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }

    public int getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonProperty("realizada_em")
    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }
}
