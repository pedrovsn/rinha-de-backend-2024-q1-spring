package io.github.pedrovsn.rinhabackend.controller.dto;

import com.fasterxml.jackson.annotation.*;

public final class CreateTransactionRequest {
    private final int valor;
    private final String tipo;
    private final String descricao;

    @JsonCreator
    public CreateTransactionRequest(
            @JsonProperty("valor") int valor,
            @JsonProperty("tipo") String tipo,
            @JsonProperty("descricao") String descricao
    ) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int valor() {
        return valor;
    }

    public String tipo() {
        return tipo;
    }

    public String descricao() {
        return descricao;
    }

}
