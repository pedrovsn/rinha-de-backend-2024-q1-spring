package io.github.pedrovsn.rinhabackend.controller;

import io.github.pedrovsn.rinhabackend.controller.dto.CreateTransactionRequest;
import io.github.pedrovsn.rinhabackend.controller.dto.CreateTransactionResponse;
import io.github.pedrovsn.rinhabackend.controller.dto.TransactionDto;
import io.github.pedrovsn.rinhabackend.controller.dto.TransactionHistoryResponse;
import io.github.pedrovsn.rinhabackend.domain.CustomerCurrentStatus;
import io.github.pedrovsn.rinhabackend.domain.CustomerTransactions;
import io.github.pedrovsn.rinhabackend.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private final TransactionService transactionService;

    public CustomerController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{customerId}/transacoes")
    public ResponseEntity<Object> createTransaction(
            @PathVariable("customerId") int customerId,
            @RequestBody CreateTransactionRequest createTransactionRequest
    ) {

        CustomerCurrentStatus customerCurrentStatus = transactionService.placeTransaction(
                customerId,
                createTransactionRequest.tipo(),
                createTransactionRequest.valor(),
                createTransactionRequest.descricao()
        );

        return ResponseEntity
                .ok(new CreateTransactionResponse(customerCurrentStatus.balance(), customerCurrentStatus.limit()));
    }

    @GetMapping("/{customerId}/extrato")
    public ResponseEntity<Object> getTransactionHistory(
            @PathVariable("customerId") int customerId
    ) {

        CustomerTransactions customerTransactions = transactionService.getCustomerTransactions(customerId);

        return ResponseEntity
                .ok(new TransactionHistoryResponse(
                        customerTransactions.balance(),
                        customerTransactions.creditLimit(),
                        customerTransactions.transactions().stream().map(t -> new TransactionDto(
                                t.amount(),
                                t.type(),
                                t.description(),
                                t.createdAt()
                        )).toList()));
    }
}
