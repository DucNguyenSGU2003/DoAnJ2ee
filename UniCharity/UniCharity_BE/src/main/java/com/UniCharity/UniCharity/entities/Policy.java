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
@Table(name = "policies", schema = "UniversityCharityDB")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @Lob
    @Column(name = "policy_description", nullable = false)
    private String policyDescription;

    @NotNull
    @Lob
    @Column(name = "eligibility_criteria", nullable = false)
    private String eligibilityCriteria;

    @Size(max = 50)
    @NotNull
    @Column(name = "approval_required", nullable = false, length = 50)
    private String approvalRequired;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "policy")
    private Set<PolicyViolation> policyViolations = new LinkedHashSet<>();

}