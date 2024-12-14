package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fund_allocations", schema = "UniversityCharityDB", indexes = {
        @Index(name = "report_id", columnList = "report_id"),
        @Index(name = "user_id", columnList = "user_id")
})
public class FundAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocation_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_id", nullable = false)
    private CampaignReport report;

    @Size(max = 500)
    @NotNull
    @Column(name = "category", nullable = false, length = 500)
    private String category;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Long amount;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}