package com.UniCharity.UniCharity.dto.response.fundAllocation;

import com.UniCharity.UniCharity.dto.response.campaignReport.CampaignReportSimple;
import com.UniCharity.UniCharity.dto.response.user.UserSimple;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FundAllocationResponse {
    Integer id;
    String category;
    Long amount;
    String description;
    LocalDate createdAt;

    CampaignReportSimple report;
    UserSimple user;
}
