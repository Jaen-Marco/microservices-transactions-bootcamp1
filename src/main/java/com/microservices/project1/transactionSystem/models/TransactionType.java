package com.microservices.project1.transactionSystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionType {
    @Id private Long id;
    private String nameTransaction; //Debit (1), Deposit (2), UseCredit (3), PayCredit (4)
}
