package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "campaigns", schema = "UniversityCharityDB", indexes = {
        @Index(name = "created_by", columnList = "created_by")
})
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "target_amount", nullable = false)
    private Long targetAmount;

    @ColumnDefault("0")
    @Column(name = "current_amount", nullable = false)
    private Long currentAmount;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    @JsonBackReference
    private User createdBy;

    @Size(max = 255)
    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "campaign")
    private Set<CampaignReport> campaignReports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "campaign")
    private Set<Donation> donations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "campaign")
    private Set<Image> images = new LinkedHashSet<>();

    @OneToMany(mappedBy = "campaign")
    private Set<Policy> policies = new LinkedHashSet<>();

}