package com.microservices.project1.transactionSystem.controllers;

import com.microservices.project1.transactionSystem.models.Transaction;
import com.microservices.project1.transactionSystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Flux<Transaction> findAllT() { return transactionService.findAll(); }

    @GetMapping("/{id}")
    public Mono<Transaction> findByIdT(@PathVariable Long id) { return transactionService.findById(id); }

    @PostMapping
    public Mono<Transaction> saveT(@RequestBody Transaction transaction) { return transactionService.save(transaction); }

    @PutMapping("/{id}")
    public Mono<Transaction> updateT(@PathVariable Long id ,@RequestBody Transaction transaction) {
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteT(@PathVariable Long id) { return transactionService.deleteById(id); }
}
