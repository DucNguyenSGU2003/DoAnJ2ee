package com.UniCharity.UniCharity.repositories;

import com.UniCharity.UniCharity.entities.Donation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    List<Donation> findAllByUserId(int userId);
    List<Donation> findAllByCampaignId(int campaignId);
    @Query("""
        SELECT u.id AS userId, u.name AS userName, SUM(d.amount) AS totalAmount
        FROM Donation d
        JOIN d.user u
        WHERE d.campaign.id = :campaignId
        GROUP BY u.id, u.name
        ORDER BY totalAmount DESC
    """)
    List<Object[]> findTopUsersByCampaignId(@Param("campaignId") int campaignId, Pageable pageable);
}
