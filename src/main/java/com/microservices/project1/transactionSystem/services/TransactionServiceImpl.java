package com.microservices.project1.transactionSystem.services;

import com.microservices.project1.transactionSystem.controllers.ProductsProxy;
import com.microservices.project1.transactionSystem.models.Transaction;
import com.microservices.project1.transactionSystem.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService{

    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private static final String ERR_MSG = "Error en la transacción";
    private static final String CRCTMSG = "Transacción realizada correctamente: ";

    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final ProductsProxy productsProxy;

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
                    existingTransaction.setAmount(transaction.getAmount());
                    existingTransaction.setIdAccount(transaction.getIdAccount());
                    existingTransaction.setDate(transaction.getDate());
                    existingTransaction.getType().setId(transaction.getType().getId());
                    existingTransaction.getType().setNameTransaction(transaction.getType().getNameTransaction());
                    return transactionRepository.save(transaction);
                });
    }

    @Override
    public Mono<Void> deleteById(Long id) { return transactionRepository.deleteById(id); }

    @Override
    public Mono<String> debit(Double amount, Transaction transaction) {
        transaction.setAmount(amount);
        Double response = productsProxy.debit(transaction.getIdAccount(), amount);
        transactionRepository.save(transaction).subscribe();
        String respuesta = response.isNaN()? ERR_MSG : CRCTMSG + response;
        logger.info(respuesta);
        return Mono.just(respuesta);
    }
    //Método opcional manejando respuestas con flatMap (validar error, posible motivo: .map(Account::getBalance);)
    /*public Mono<String> debitA(Double amount, Transaction transaction) {
        return productsProxy.debit(transaction.getIdAccount(), amount)
                .flatMap(response -> {
                    // La operación productsProxy.debit() ha terminado aquí
                    transactionRepository.save(transaction).subscribe(); // Guardar la transacción en la base de datos
                    String respuesta = response.isNaN() ? ERR_MSG : CRCTMSG + response;
                    logger.info(respuesta);
                    return Mono.just(respuesta);
                });
    }*/
    @Override
    public Mono<String> deposit(Double amount, Transaction transaction) {
        transaction.setAmount(amount);
        Double response = productsProxy.deposit(transaction.getIdAccount(), amount);
        transactionRepository.save(transaction).subscribe();
        String respuesta = response.isNaN()? ERR_MSG : CRCTMSG + response;
        logger.info(respuesta);
        return Mono.just(respuesta);
    }

    @Override
    public Mono<String> payCredit(Double amount, Transaction transaction) {
        transaction.setAmount(amount);
        Double response = productsProxy.payCredit(transaction.getIdAccount(), amount);
        transactionRepository.save(transaction).subscribe();
        String respuesta = response.isNaN()? ERR_MSG : CRCTMSG + response;
        return Mono.just(respuesta);
    }

    @Override
    public Mono<String> useCredit(Double amount, Transaction transaction) {
        transaction.setAmount(amount);
        Double response = productsProxy.useCredit(transaction.getIdAccount(), amount);
        transactionRepository.save(transaction).subscribe();
        String respuesta = response.isNaN()? ERR_MSG : CRCTMSG + response;
        return Mono.just(respuesta);
    }
}
