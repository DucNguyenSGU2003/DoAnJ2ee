package com.UniCharity.UniCharity.repositories;

import com.UniCharity.UniCharity.entities.FundAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundAllocationRepository extends JpaRepository<FundAllocation, Integer> {
    List<FundAllocation> findByReportId(Integer reportId);
}
