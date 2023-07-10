package com.microservices.project1.transactionSystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id private Long id;
    private String type;
    private Double amount;
    private Long id_sendingClient;
    private Long id_receivingClient;
    private String date;
}
