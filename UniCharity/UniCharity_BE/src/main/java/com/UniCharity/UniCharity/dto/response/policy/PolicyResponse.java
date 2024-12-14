package com.UniCharity.UniCharity.dto.response.policy;

import com.UniCharity.UniCharity.dto.response.campaign.CampaignSimple;
import com.UniCharity.UniCharity.dto.response.policyViolation.PolicyViolationResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PolicyResponse {
    Integer id;
    String policyDescription;
    String eligibilityCriteria;
    String approvalRequired;
    LocalDate createdAt;
    LocalDate updatedAt;

    CampaignSimple campaign;

    List<PolicyViolationResponse> policyViolations;
}
