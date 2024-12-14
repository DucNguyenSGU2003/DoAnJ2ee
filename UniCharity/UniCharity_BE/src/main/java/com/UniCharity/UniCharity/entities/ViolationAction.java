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
@Table(name = "violation_actions", schema = "UniversityCharityDB", indexes = {
        @Index(name = "violation_id", columnList = "violation_id")
})
public class ViolationAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "violation_id", nullable = false)
    private PolicyViolation violation;

    @NotNull
    @Lob
    @Column(name = "action_description", nullable = false)
    private String actionDescription;

    @NotNull
    @Column(name = "action_date", nullable = false)
    private LocalDate actionDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

}