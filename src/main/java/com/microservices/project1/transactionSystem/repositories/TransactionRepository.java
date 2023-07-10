package com.microservices.project1.transactionSystem.repositories;

import com.microservices.project1.transactionSystem.models.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction,Long> {
}
