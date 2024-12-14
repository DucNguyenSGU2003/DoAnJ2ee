package com.UniCharity.UniCharity.repositories;

import com.UniCharity.UniCharity.entities.PolicyViolation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyViolationRepository extends JpaRepository<PolicyViolation, Integer> {
}
