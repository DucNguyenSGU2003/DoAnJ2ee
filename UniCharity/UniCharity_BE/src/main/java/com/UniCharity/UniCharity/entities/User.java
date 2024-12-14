package com.UniCharity.UniCharity.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "UniversityCharityDB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 50)
    @Column(name = "phone", length = 50)
    private String phone;

    @Size(max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "role")
    @NotNull
    private String role;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @OneToMany(mappedBy = "createdBy")
    private Set<Campaign> campaigns = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Donation> donations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<FundAllocation> fundAllocations = new LinkedHashSet<>();

}