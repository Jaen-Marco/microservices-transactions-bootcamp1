package com.microservices.project1.transactionSystem.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import reactor.core.publisher.Mono;

@FeignClient(name = "api-product", url = "localhost:8082")
public interface ProductsProxy {

    @PutMapping("api/products/account/{idAccount}/debit={amount}")
    Double debit(@PathVariable Long idAccount, @PathVariable Double amount);

    @PutMapping("api/products/account/{idAccount}/deposit={amount}")
    Double deposit(@PathVariable Long idAccount, @PathVariable Double amount);

    @PutMapping("/api/products/credits/{idAccount}/payment/amount={amount}")
    Double payCredit(@PathVariable Long idAccount, @PathVariable Double amount);

    @PutMapping("/api/products/credits/{idAccount}/useCredit/amount={purchaseAmount}")
    Double useCredit(@PathVariable Long idAccount, @PathVariable Double amount);
}
