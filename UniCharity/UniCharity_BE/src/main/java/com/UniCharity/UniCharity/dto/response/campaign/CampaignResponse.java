package com.UniCharity.UniCharity.dto.response.campaign;

import com.UniCharity.UniCharity.dto.response.campaignReport.CampaignReportResponse;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.image.ImageResponse;
import com.UniCharity.UniCharity.dto.response.policy.PolicyResponse;
import com.UniCharity.UniCharity.dto.response.user.UserSimple;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignResponse {
    Integer id;
    String title;
    String description;
    Long targetAmount;
    Long currentAmount;
    LocalDate createdAt;
    LocalDate startDate;
    LocalDate endDate;
    String status;

    UserSimple createdBy;

    List<ImageResponse> images;
    List<PolicyResponse> policies;
    List<DonationResponse> donations;
    List<CampaignReportResponse> campaignReports;
}
