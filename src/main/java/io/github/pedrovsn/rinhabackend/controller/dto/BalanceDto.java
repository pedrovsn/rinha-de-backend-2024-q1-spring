package io.github.pedrovsn.rinhabackend.controller.dto;

import com.fasterxml.jackson.annotation.*;
import java.time.*;

public final class BalanceDto {

    private final int total;
    private final int limite;
    private final LocalDateTime dataExtrato;

    @JsonCreator
    public BalanceDto(
            int total,
            int limite,
            LocalDateTime dataExtrato
    ) {
        this.total = total;
        this.limite = limite;
        this.dataExtrato = dataExtrato;
    }

    public int getTotal() {
        return total;
    }

    public int getLimite() {
        return limite;
    }

    @JsonProperty("data_extrato")
    public LocalDateTime getDataExtrato() {
        return dataExtrato;
    }

}
