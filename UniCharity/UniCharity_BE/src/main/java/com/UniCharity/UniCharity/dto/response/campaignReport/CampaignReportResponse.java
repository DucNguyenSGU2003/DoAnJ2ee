package com.UniCharity.UniCharity.dto.response.campaignReport;

import com.UniCharity.UniCharity.dto.response.fundAllocation.FundAllocationResponse;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignSimple;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignReportResponse {
    Integer id;
    Long totalDonations;
    Long totalRecipients;
    String resultsSummary;
    String lessonsLearned;
    LocalDate reportDate;
    LocalDate createdAt;
    LocalDate updatedAt;

    CampaignSimple campaign;

    List<FundAllocationResponse> fundAllocations;
}
