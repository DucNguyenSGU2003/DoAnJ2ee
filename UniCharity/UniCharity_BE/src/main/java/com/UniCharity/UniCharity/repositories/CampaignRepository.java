package com.UniCharity.UniCharity.repositories;

import com.UniCharity.UniCharity.entities.Campaign;
import com.UniCharity.UniCharity.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    List<Campaign> findByStatus(String status, Sort sort);
    List<Campaign> findByTitleContainingIgnoreCase(String title, Sort sort);
    @Query(value = "SELECT MONTH(c.created_at) AS month, COUNT(c.campaign_id) AS count " +
            "FROM campaigns c " +
            "WHERE YEAR(c.created_at) = :year " +
            "GROUP BY MONTH(c.created_at) " +
            "ORDER BY month ASC", nativeQuery = true)
    List<Object[]> countCampaignsByMonth(@Param("year") int year);
    @Query("SELECT c FROM Campaign c WHERE FUNCTION('YEAR', c.createdAt) = :year")
    List<Campaign> findCampaignsByYear(@Param("year") int year);
}
