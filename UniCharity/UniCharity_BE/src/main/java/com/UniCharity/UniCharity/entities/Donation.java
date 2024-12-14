package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "donations", schema = "UniversityCharityDB", indexes = {
        @Index(name = "campaign_id", columnList = "campaign_id"),
        @Index(name = "user_id", columnList = "user_id")
})
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Size(max = 50)
    @NotNull
    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @NotNull
    @Column(name = "donation_date", nullable = false)
    private LocalDateTime donationDate;

    @OneToMany(mappedBy = "donation")
    private Set<Transaction> transactions = new LinkedHashSet<>();
}