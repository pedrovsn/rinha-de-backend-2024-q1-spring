package io.github.pedrovsn.rinhabackend.controller;

import io.github.pedrovsn.rinhabackend.controller.dto.*;
import io.github.pedrovsn.rinhabackend.domain.CustomerCurrentStatus;
import io.github.pedrovsn.rinhabackend.domain.CustomerTransactions;
import io.github.pedrovsn.rinhabackend.exception.*;
import io.github.pedrovsn.rinhabackend.service.TransactionService;
import java.time.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private final TransactionService transactionService;

    public CustomerController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(
            value = "/{customerId}/transacoes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateTransactionResponse> createTransaction(
            @PathVariable("customerId") int customerId,
            @RequestBody CreateTransactionRequest createTransactionRequest
    ) {

        try {
            CustomerCurrentStatus customerCurrentStatus = transactionService.placeTransaction(
                    customerId,
                    createTransactionRequest.tipo(),
                    createTransactionRequest.valor(),
                    createTransactionRequest.descricao()
            );

            return ResponseEntity
                    .ok(new CreateTransactionResponse(customerCurrentStatus.balance(), customerCurrentStatus.limit()));
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity
                    .notFound().build();
        } catch (InvalidAttributeException | InsufficientFundsException ex) {
            return ResponseEntity
                    .unprocessableEntity().build();
        }
    }

    @GetMapping("/{customerId}/extrato")
    public ResponseEntity<TransactionHistoryResponse> getTransactionHistory(
            @PathVariable("customerId") Integer customerId
    ) {

        try {
            CustomerTransactions customerTransactions = transactionService.getCustomerTransactions(customerId);

            return ResponseEntity
                    .ok(new TransactionHistoryResponse(
                            new BalanceDto(
                                    customerTransactions.balance(),
                                    customerTransactions.creditLimit(),
                                    LocalDateTime.now()
                            ),
                            customerTransactions.transactions().stream().map(t -> new TransactionDto(
                                    t.amount(),
                                    t.type(),
                                    t.description(),
                                    t.createdAt()
                            )).toList()));
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity
                    .notFound().build();
        }
    }

}
