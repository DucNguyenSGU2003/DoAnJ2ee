package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "policy_violations", schema = "UniversityCharityDB", indexes = {
        @Index(name = "policy_id", columnList = "policy_id")
})
public class PolicyViolation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "violation_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @NotNull
    @Lob
    @Column(name = "violation_description", nullable = false)
    private String violationDescription;

    @NotNull
    @Column(name = "violation_date", nullable = false)
    private LocalDate violationDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "violation")
    private Set<ViolationAction> violationActions = new LinkedHashSet<>();

}