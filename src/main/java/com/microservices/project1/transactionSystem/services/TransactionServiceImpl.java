package com.microservices.project1.transactionSystem.services;

import com.microservices.project1.transactionSystem.models.Transaction;
import com.microservices.project1.transactionSystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Flux<Transaction> findAll() { return transactionRepository.findAll(); }

    @Override
    public Mono<Transaction> findById(Long id) { return transactionRepository.findById(id); }

    @Override
    public Mono<Transaction> save(Transaction transaction) { return transactionRepository.save(transaction); }

    @Override
    public Mono<Transaction> update(Long id, Transaction transaction) {
        return transactionRepository.findById(id)
                .flatMap(existingTransaction -> {
                    existingTransaction.setType(transaction.getType());
                    existingTransaction.setAmount(transaction.getAmount());
                    existingTransaction.setId_sendingClient(transaction.getId_sendingClient());
                    existingTransaction.setId_receivingClient(transaction.getId_receivingClient());
                    existingTransaction.setDate(transaction.getDate());
                    return transactionRepository.save(transaction);
                });
    }

    @Override
    public Mono<Void> deleteById(Long id) { return transactionRepository.deleteById(id); }
}
