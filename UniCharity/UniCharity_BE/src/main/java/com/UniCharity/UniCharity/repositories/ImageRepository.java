package com.UniCharity.UniCharity.repositories;

import com.UniCharity.UniCharity.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByCampaignId(int campaignId);
}
