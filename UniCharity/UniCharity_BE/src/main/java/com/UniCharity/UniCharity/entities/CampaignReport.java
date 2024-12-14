package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "campaign_reports", schema = "UniversityCharityDB", indexes = {
        @Index(name = "campaign_id", columnList = "campaign_id")
})
public class CampaignReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @Column(name = "total_donations", nullable = false)
    private Long totalDonations;

    @NotNull
    @Column(name = "total_recipients", nullable = false)
    private Long totalRecipients;

    @NotNull
    @Lob
    @Column(name = "results_summary", nullable = false)
    private String resultsSummary;

    @NotNull
    @Lob
    @Column(name = "lessons_learned", nullable = false)
    private String lessonsLearned;

    @NotNull
    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "report")
    private Set<FundAllocation> fundAllocations = new LinkedHashSet<>();

}