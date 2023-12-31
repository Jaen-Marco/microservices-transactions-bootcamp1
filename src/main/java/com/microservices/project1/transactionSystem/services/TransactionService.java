package com.microservices.project1.transactionSystem.services;

import com.microservices.project1.transactionSystem.models.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Flux<Transaction> findAll();
    Mono<Transaction> findById(Long id);
    Mono<Transaction> save(Transaction transaction);
    Mono<Transaction> update(Long id, Transaction transaction);
    Mono<Void> deleteById (Long id);

    Mono<String> debit(Double amount, Transaction transaction);
    Mono<String> deposit(Double amount, Transaction transaction);
    Mono<String> payCredit(Double amount, Transaction transaction);
    Mono<String> useCredit(Double amount, Transaction transaction);
}
