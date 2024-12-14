package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions", schema = "UniversityCharityDB", indexes = {
        @Index(name = "donation_id", columnList = "donation_id")
})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @Size(max = 255)
    @Column(name = "transaction_code", nullable = false)
    private String transactionCode;

    @Size(max = 50)
    @Column(name = "payment_gateway", nullable = false, length = 50)
    private String paymentGateway;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Size(max = 255)
    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Size(max = 500)
    @Column(name = "response_code", nullable = false, length = 500)
    private String responseCode;

    @Size(max = 500)
    @Column(name = "transaction_description", nullable = false, length = 500)
    private String transactionDescription;
}